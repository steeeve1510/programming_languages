from elements.Entity import Entity
from elements.Element import Element
import Utils

from ImplicitEntities import ImplicitEntities

class Pool(Element):

	def __init__(self, entities):
		super(Pool, self).__init__(entities);
		self.entities = []

		self.parseCode(entities);
		self.addImplicitEntities();

	def parseCode(self, code):
		code = code[1:-1]

		bracketsOpen = 0;
		curlyBracketsOpen = 0;

		nextEntity = "";
		for c in code:
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


		if nextEntity.strip() != "" and nextEntity.strip() != "}":
			self.entities.append(Entity(nextEntity.strip()));
		
	def addImplicitEntities(self):
		ImplicitEntities().addEntitiesToPool(self);



	def bindVariables(self, name, value):
		for i in range(0, len(self.entities)):
			if not self.entities[i].isImplicit:
				self.entities[i].bindVariables(name, value);

	def __str__(self):
		s = "{\n";
		for entity in self.entities:
			s += "\t" + str(entity) + ";\n"
		s += "}\n";
		return s;

		'''
		s = "code:\n"
		s += Utils.addTabsToLines(self.code, 1) + "\n";

		s += "Entities: (" + str(len(self.entities)) + ")"
		for en in self.entities:
			s += Utils.addTabsToLines(en.__str__(), 1);

		return s;
		'''

	def __eq__(self, other):
		if not isinstance(other, Pool):
			return False;

		if self.entities != other.entities:
			return False;

		return True;