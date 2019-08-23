from implicitEntities.Print import Print;
from implicitEntities.Addition import Addition;
from implicitEntities.Subtraction import Subtraction;
from implicitEntities.Division import Division;
from implicitEntities.Multiplication import Multiplication;
from implicitEntities.Eq import Eq;
from implicitEntities.Ne import Ne;
from implicitEntities.Lt import Lt;
from implicitEntities.Gt import Gt;
from implicitEntities.Le import Le;
from implicitEntities.Ge import Ge;
from implicitEntities.Exit import Exit;
from implicitEntities.ReadLine import ReadLine;

class ImplicitEntities:

	def __init__(self):
		pass

	def addEntitiesToPool(self, pool):
		pool.entities.append(Print());
		pool.entities.append(Addition());
		pool.entities.append(Subtraction());
		pool.entities.append(Division());
		pool.entities.append(Multiplication());
		pool.entities.append(Eq());
		pool.entities.append(Ne());
		pool.entities.append(Lt());
		pool.entities.append(Gt());
		pool.entities.append(Le());
		pool.entities.append(Ge());
		pool.entities.append(Exit());
		pool.entities.append(ReadLine());