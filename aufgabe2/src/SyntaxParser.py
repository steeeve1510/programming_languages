class SyntaxParser:
	def __init__(self):
		pass;

	def parse(self, code):
		code = code.strip()
		if not code.startswith('{') or not code.endswith('}'):
			return False;
		return True;