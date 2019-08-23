import argparse
import sys
import logging
from SyntaxParser import SyntaxParser
from elements.Pool import Pool
import copy

logging.getLogger().setLevel(logging.INFO)

class Interpreter:
	syntaxParser = SyntaxParser();

	def __init__(self):
		parser = argparse.ArgumentParser(description='Interpreter')

		parser.add_argument(
			'code',
			metavar='code',
			type=str,
			help='program code or file to be interpreted'
		)

		args = parser.parse_args()

		code = args.code;



		if not args.code.startswith('{'):
			with open(args.code, 'r') as file:
				code = file.read()

		# remove comments
		lines = code.splitlines(True);
		code = "".join([x for x in lines if not x.strip().startswith("#")])



		if not self.syntaxParser.parse(code):
			logging.error("Invalid Syntax")
			sys.exit(1)


		pool = Pool(code)

		pool = self.interpretPool(pool)


	def interpretPool(self, pool):

		#interpret entities until no more left
		while True:
			b = False;
			for entity in pool.entities:
				if entity.isImplicit == False:
					pool = self.interpretNextEntity(pool);
					b = True;
					break;

			if not b:
				break;

		return pool

	def createLinkedSet(self, pool, e_n):
		E = [e_n]
		R = [e_n]

		remainingEntities = []
		for entity in pool[0].entities:
			remainingEntities.append(entity)

		remainingEntities.remove(e_n);

		params = {
			"pool": pool[0],
			"E": E,
			"R": R,
			"remainingEntities": remainingEntities
		}
		linkedSetFound = self.interpretLinks(params)

		pool[0] = params["pool"]


		if not linkedSetFound:
			logging.debug("no linked set found")
			raise Exception("No linked set found")



	def interpretLinks(self, param):
		pool = param["pool"]
		E = param["E"]
		R = param["R"]
		remainingEntities = param["remainingEntities"]

		r_n = None

		#select next entity from R
		for entity in R:
			if len(entity.links) > 0:
				r_n = entity;

		if r_n == None:
			logging.debug("linked set found")
			self.linkedSetFound(pool, E, R)
			return True

		# find matching link with entities that are not in E
		nextLink = r_n.links[0]
		if nextLink.pool != None:

			# catch exception in case pool cannot be interpreted
			try:
				_pool = self.interpretPool(nextLink.pool)
			except Exception as e:
				return False

			matches = self.findLinkMatches(_pool.entities, nextLink)
		else:
			matches = self.findLinkMatches(remainingEntities, nextLink)


		if len(matches) == 0:
			return False;

		nextLink_backup = copy.deepcopy(nextLink)
		pool_backup = copy.deepcopy(pool);
		E_backup = copy.deepcopy(E);
		R_backup = copy.deepcopy(R);
		remainingEntities_backup = copy.deepcopy(remainingEntities);

		linkedSetFound = False

		# try all matches until a valid one is found
		for entity in matches:

			e_n = copy.deepcopy(entity);

			# catch exceptions of implicit entities
			try:
				e_n.run(nextLink)
			except Exception as e:
				logging.debug("error running entity: " + type(e).__name__)
				return False


			r_n2 = r_n.applyBindings(e_n.links[0])
			e_n2 = e_n.applyBindings(r_n.links[0])

			E.append(e_n)
			if nextLink.pool == None:
				remainingEntities.remove(e_n)

			R.remove(r_n)

			R.append(r_n2);
			R.append(e_n2);

			linkedSetFound = self.interpretLinks({
				"pool": pool,
				"E": E,
				"R": R,
				"remainingEntities": remainingEntities
			})

			if linkedSetFound:
				param["pool"] = pool
				param["E"] = E
				param["R"] = R
				param["remainingEntities"] = remainingEntities
				break;
			else:
				nextLink = copy.deepcopy(nextLink_backup)
				pool = copy.deepcopy(pool_backup);
				E = copy.deepcopy(E_backup);
				R = copy.deepcopy(R_backup);
				remainingEntities = copy.deepcopy(remainingEntities_backup);


		return linkedSetFound;

	def interpretNextEntity(self, pool):

		pool = [pool];
		self.createLinkedSet(pool, pool[0].entities[0])
		return pool[0];


	def linkedSetFound(self, pool, E, R):

		for entity in E:
			if not entity.dots and entity in pool.entities:
				pool.entities.remove(entity)

		for entity in R:
			for i in range(0, len(entity.entities)):
				entity.entities[0].isImplicit = entity.isImplicit;
				pool.entities.insert(i, entity.entities[i])


		return pool


	def findLinkMatches(self, entities, link):
		matches = [];
		for entity in entities:
			if len(entity.links) == 0:
				continue;

			if link.linksMatch(entity.links[0]):
				matches.append(entity)

		return matches



i = Interpreter();