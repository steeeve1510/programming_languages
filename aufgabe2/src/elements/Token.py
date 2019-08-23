from elements.Element import Element
import Utils

class Token(Element):

	def __init__(self, code):
		super(Token, self).__init__(code);

		self.string = None;
		self.pool = None;
		self.id = None;

		self.parseCode(code);


	def parseCode(self, code):
		code = code.strip();

		if code.startswith("\""):
			self.string = code;
		elif code.startswith("{"):

			#to circumvent circular dependency
			from elements.Pool import Pool
			self.pool = Pool(code);
		else:
			self.id = code;


	def bindVariables(self, name, value):
		if self.id == ("!" + name):
			if value.startswith("\""):
				self.string = value;
				self.id = None;
			else:				
				self.string = None;
				self.id = value;

			self.code = value

	def __str__(self):
		return self.string if self.string != None else self.id

		'''
		s = "";

		if self.string != None:
			return "String: " + self.string;

		if self.pool != None:
			return "Pool:" + Utils.addTabsToLines(self.pool.__str__(), 2);

		if self.id != None:
			return "Id: " + self.id;
		'''

	def __eq__(self, other):
		if not isinstance(other, Token):
			return False;

		if self.string != other.string:
			return False;

		if self.id != other.id:
			return False;

		if self.pool != other.pool:
			return False;

		return True;