
file:///D:/workspaces/cartographie/jacoco-master/org.jacoco.doc/docroot/doc/api.html
org.jacoco.examples.CoreTutorial


Utiliser Jacoco pour tracer l'execution : 
- threadlocal pour avoir l'id du thread courant et diff�rencier chaque appel ?
	on a ca dans les logger
- a t'on un timestamp pour chaque ligne ? peut-on savoir dans quel ordre ca �t� ex�cut� ????
	impl�menter un compteur ? 
	quid des ex�cutions parall�les ?
- avoir en plus l'�tat des donn�es utilis�es 
	faire carr�ment le flow de changement des donn�es 
- avoir le code source de chaque ligne ex�cut�e afin de tracer l'ex�cution
	on peut raccrocher les wagons apr�s avec les sources ... on a le num�ro de ligne
	ca devrait le faire en utilisant la meme m�thode que pour la couverture
- identification des zones utilis�es afin de pr�parer un eclatement de l'appli en diff�rents micro-services


Solution pour capturer le source code :
https://stackoverflow.com/questions/35553646/using-asm-for-bytecode-analysis



http://archive.oreilly.com/pub/a/onjava/2004/10/06/asm1.html?page=2
http://www.baeldung.com/java-asm

	org.jacoco.core.internal.flow.ClassProbesAdapter
	org.jacoco.core.internal.instr.ClassInstrumenter
	org.jacoco.core.instr.Instrumenter

https://github.com/eugenp/tutorials/tree/master/asm


http://www.gcuisinier.net/blog/2012/06/15/javaagents/
http://jboss-javassist.github.io/javassist/
https://zeroturnaround.com/rebellabs/how-to-inspect-classes-in-your-jvm/
https://www.niceideas.ch/roller2/badtrash/entry/bytecode-manipulation-with-javassist-for1

https://github.com/jacoco/jacoco/blob/master/org.jacoco.core/src/org/jacoco/core/instr/Instrumenter.java

http://www.jacoco.org/jacoco/trunk/doc/offline.html
https://stackoverflow.com/questions/6856744/getting-code-coverage-of-my-application-using-jacoco-java-agent-on-tomcat

