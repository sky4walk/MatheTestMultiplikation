set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_131
#set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_111

set PATH=%JAVA_HOME%\bin;%PATH%
set PATH=%JAVA_HOME%\bin\jre\bin;%PATH%
set PATH=%JAVA_HOME%\bin\jre:%PATH%;
set CLASSPATH=%PATH%;%CD%

del *.class
javac MatheTask.java -g -d ./
javac MatheTest.java

java MatheTest

cmd .