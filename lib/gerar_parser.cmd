java -jar java-cup-11a.jar -parser Parser -expect 1 <..\src\compiler\syntax\Parser.cup
move /Y *.java ..\src\compiler\syntax\
@if [%1]==[] pause