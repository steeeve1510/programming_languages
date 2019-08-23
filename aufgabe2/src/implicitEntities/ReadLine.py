from implicitEntities.ImplicitEntity import ImplicitEntity


class ReadLine(ImplicitEntity):
	
	def __init__(self):		
		super(ReadLine, self).__init__("readline [read !n ..]");

	def run(self, match):
		line = input('input: ');
		self.entities[0].links[0].tokens[1].id = line