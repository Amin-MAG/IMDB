
import subprocess as sub
import os

cur_dir = dir_path = os.path.dirname(os.path.realpath(__file__))

run = r"cls && color 0a"
sub.call(run, shell=True)
run = r"javac -cp " + cur_dir + "\jsoup-1.11.3.jar;" + cur_dir + "\json-simple-1.1.1.jar; Main.java"
sub.call(run, shell=True)
run = r"java -cp " + cur_dir + "\jsoup-1.11.3.jar;" + cur_dir + "\json-simple-1.1.1.jar; Main"
sub.call(run, shell=True)

