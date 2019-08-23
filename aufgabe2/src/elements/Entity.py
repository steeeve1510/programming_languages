from elements.Link import Link
from elements.Element import Element
import Utils
import copy

class Entity(Element):

	def __init__(self, code):		
		super(Entity, self).__init__(code);
		self.entities = [];
		self.links = [];
		self.dots = False;

		self.code = code;

		self.isImplicit = False

		self.parseCode(code);



	def parseCode(self, code):
		s = code.split('[');
		if len(s) >= 2:
			part2 = "[".join(s[1:]).strip();
			part2 = part2[0:-1].strip();
			if part2.endswith(".."):
				self.dots = True;
				part2 = part2[0:-2].strip();

			entitiesCode = part2
			#for en in entitiesCode:
			#	self.entities.append(Entity(en));

			bracketsOpen = 0;
			curlyBracketsOpen = 0;
			nextEntity = "";
			for c in entitiesCode:
				if c == "[":
					bracketsOpen += 1;
				elif c == "]":
					bracketsOpen -= 1;
				elif c == "{":
					curlyBracketsOpen += 1;
				elif c == "}":
					curlyBracketsOpen -= 1;

				if c == ";" and bracketsOpen == 0 and curlyBracketsOpen == 0:
					self.entities.append(Entity(nextEntity.strip()));
					nextEntity = "";
					continue;

				nextEntity += c;


			if nextEntity.strip() != "":
				self.entities.append(Entity(nextEntity.strip()));



		linkCode = s[0].strip();

		nextLink = "";

		curlyBracketsOpen = 0;
		for c in linkCode:
			if c == "{":
				curlyBracketsOpen += 1;
			elif c == "}":
				curlyBracketsOpen -= 1;

			if c == "&" and curlyBracketsOpen == 0:
				self.links.append(Link(nextLink.strip()));
				nextLink = "";
				continue;

			nextLink += c;


		if nextLink.strip() != "":
			self.links.append(Link(nextLink.strip()));

	

	def applyBindings(self, link):
		entityCopy = copy.deepcopy(self) 

		for i in range(0, len(self.links[0].tokens)):
			if self.links[0].tokens[i].id != None and self.links[0].tokens[i].id.startswith("?"):
				value = link.tokens[i].id if link.tokens[i].id != None else link.tokens[i].string
				entityCopy.bindVariables(self.links[0].tokens[i].id[1:], value, True)

		entityCopy.links.pop(0);
		return entityCopy
	




	def bindVariables(self, name, value, skipFirstLink=False):
		start = 1 if skipFirstLink else 0;
		for i in range(start, len(self.links)):
			self.links[i].bindVariables(name, value);

		for i in range(0, len(self.entities)):
			self.entities[i].bindVariables(name, value)

	def run(self, match):
		pass;



	def __str__(self):

		s = "";

		for link in self.links:
			s += str(link) + " & "

		if s != "":
			s = s[0:-3]

		if self.entities:
			s += " ["
			for entity in self.entities:
				s += str(entity) + "; "
			s += "]"
		return s;

		'''
		s = "code:\n\t" + self.code + "\n";

		s += "links: (" + str(len(self.links)) + ")"
		for l in self.links:
			s += Utils.addTabsToLines(l.__str__(), 1);

		if self.entities != []:
			s += "entities:"
			for e in self.entities:
				s += Utils.addTabsToLines(e.__str__(), 1);

		s += "dots: " + str(self.dots) +"\n"

		return s;
		'''


	def __eq__(self, other):
		if not isinstance(other, Entity):
			return False;

		#ignore entities of implicit entities because they often have special functionality that rewrites those
		if self.isImplicit == False and self.entities != other.entities:
			return False;

		if self.links != other.links:
			return False;

		if self.dots != other.dots:
			return False;

		return True;