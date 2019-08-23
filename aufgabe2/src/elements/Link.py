from elements.Token import Token
from elements.Element import Element
import Utils

class Link(Element):


	def __init__(self, code):
		super(Link, self).__init__(code);

		self.tokens = [];
		self.pool = None;
		self.id = None;


		self.parseCode(code);


	def parseCode(self, code):
		s = code.split('@');
		if len(s) >= 2:
			part2 = "@".join(s[1:]).strip();
			if part2.startswith('{'):

				#to circumvent circular dependency
				from elements.Pool import Pool

				self.pool = Pool(part2);
			elif part2.startswith('!'):
				self.id = part2;

		
		isString = False
		curlyBracketsOpen = 0;

		nextToken = "";

		for c in s[0]:
			if c == "\"":
				isString = not isString;
			elif c == "{":
				curlyBracketsOpen += 1;
			elif c == "}":
				curlyBracketsOpen -= 1;

			if c == " " and not isString and curlyBracketsOpen == 0:
				#print(nextToken.strip())
				self.tokens.append(Token(nextToken.strip()));
				nextToken = "";
				continue;

			nextToken += c

		if nextToken.strip() != "":
			#print(nextToken.strip())
			self.tokens.append(Token(nextToken.strip()));


	def bindVariables(self, name, value):
		for token in self.tokens:
			token.bindVariables(name, value);

		if self.pool != None:
			self.pool.bindVariables(name, value);

	def linksMatch(self, link):
		if len(self.tokens) != len(link.tokens):
			return False;


		for i in range(0, len(self.tokens)):
			#e.g.:  print ?n; print a
			#if self.tokens[i].id.startswith("?"):
			#	return False

			#e.g.:  print n; print !a
			#if link.tokens[i].id.startswith("!"):
			#	return False

			if link.tokens[i].id != None and link.tokens[i].id.startswith("!"):
				return False

			if self.tokens[i].id != None and self.tokens[i].id.startswith("!"):
				return False


			if self.tokens[i].string != None:
				#e.g.:  print "a"; print "b"
				if link.tokens[i].string != None and self.tokens[i].string != link.tokens[i].string:
					return False

				#e.g.:  print "a"; print b				
				if link.tokens[i].id != None and self.tokens[i].string != link.tokens[i].id and not link.tokens[i].id.startswith("?"):
					return False

			if link.tokens[i].string != None:
				#e.g.:  print a; print "b"
				if self.tokens[i].id != None and not self.tokens[i].id.startswith("!") and self.tokens[i].id != link.tokens[i].string:
					return False

			if link.tokens[i].id != None and self.tokens[i].id != None:
				#e.g.: print a; print b
				if not self.tokens[i].id.startswith("?") and not link.tokens[i].id.startswith("?") and link.tokens[i].id != self.tokens[i].id:
					return False




			if self.tokens[i].id != None and self.tokens[i].id.startswith("?") and link.tokens[i].id != None and link.tokens[i].id.startswith("?"):
				return False
						
			if self.tokens[i].id != None and self.tokens[i].id.startswith("!") and link.tokens[i].id != None and link.tokens[i].id.startswith("!"):
				return False


		return True

	def __str__(self):
		s = "";

		for token in self.tokens:
			s += str(token) + " "

		if s != "":
			s = s[0:-1];


		if self.pool:
			p = "";
			p += " @" + str(self.pool)
			p = Utils.addTabsToLines(p, 1)
			s += p;




		if self.id:
			s += " @!" + str(self.id)

		return s;


		'''
		s = "code:\n\t" + self.code + "\n";

		s += "tokens: (" + str(len(self.tokens)) + ")\n"
		for t in self.tokens:
			s += "\t" + t.__str__() + "\n";

		if self.pool != None:
			s+= "pool:"
			pool = self.pool.__str__();
			s += Utils.addTabsToLines(pool, 1);

		if self.id != None:
			s+= "\nid:"
			s += "\t" + self.id.__str__();

		return s;'''



	def __eq__(self, other):
		if not isinstance(other, Link):
			return False;

		if self.tokens != other.tokens:
			return False;

		if self.id != other.id:
			return False;

		if self.pool != other.pool:
			return False;

		return True;