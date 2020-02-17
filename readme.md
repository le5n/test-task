## Steps to start: ##

* SBT installed
* From console: sbt "run -p *port* -n *names, separated by comma* -s *statuses, separated by comma*" 
* From SBT shell: same with out quotes

Order of params can be random, amount of names and statuses doesn't have to correlate

##### Notes #####

* Validation of console params is not full as it's not in requirements
* At least one param should be passed by "-n" and "-s" options
* If port value is not passed server will run on 8080 by default 
* As far as actors logic is not implemented yet, scheduling process is done without akka features
  and might be slightly different from requirements 

##### Ambiguous places #####

'Status' field of person should change randomly? Now it's implemented to be changed randomly

