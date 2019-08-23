from implicitEntities.ImplicitEntity import ImplicitEntity


class Ne(ImplicitEntity):
	
	def __init__(self):		
		super(Ne, self).__init__("?a <> ?b [val !a ..]");

	def run(self, match):
		val1 = match.tokens[0].id if match.tokens[0].id != None else match.tokens[0].string[1:-1]
		val2 = match.tokens[2].id if match.tokens[2].id != None else match.tokens[2].string[1:-1]
		if val1 != val2:
			self.entities[0].links[0].tokens[1].id = "true"
		else:
			self.entities[0].links[0].tokens[1].id = "false"