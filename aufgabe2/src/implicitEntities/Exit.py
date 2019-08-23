from implicitEntities.ImplicitEntity import ImplicitEntity
import sys


class Exit(ImplicitEntity):
	
	def __init__(self):		
		super(Exit, self).__init__("exit ?code");

	def run(self, match):
		code = int(match.tokens[1].id);
		#print("exiting with status code: " + str(code))
		sys.exit(code)