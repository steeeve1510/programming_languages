from implicitEntities.ImplicitEntity import ImplicitEntity


class Print(ImplicitEntity):
	
	def __init__(self):		
		super(Print, self).__init__("print ?n [..]");

	def run(self, match):
		if match.tokens[1].id != None:
			print(match.tokens[1].id)

		if match.tokens[1].string != None:
			print(match.tokens[1].string[1:-1])