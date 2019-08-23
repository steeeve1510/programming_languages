def addTabsToLines(myStr, numberOfTabs):
	t = "";
	for i in range(0, numberOfTabs):
		t += "\t";

	
	return t.join(('\n' + myStr.lstrip()).splitlines(True))