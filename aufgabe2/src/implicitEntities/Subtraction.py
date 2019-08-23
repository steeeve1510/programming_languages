from implicitEntities.ImplicitEntity import ImplicitEntity


class Subtraction(ImplicitEntity):
	
	def __init__(self):		
		super(Subtraction, self).__init__("?a - ?b [val !a ..]");

	def run(self, match):
		newValue = int(match.tokens[0].id) - int(match.tokens[2].id)
		self.entities[0].links[0].tokens[1].id = str(newValue)