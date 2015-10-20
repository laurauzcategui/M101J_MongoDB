/*
Construct a query to calculate the number of messages sent by Andrew Fastow, CFO, to Jeff Skilling, 
the president. 

Andrew Fastow's email addess was andrew.fastow@enron.com. 
Jeff Skilling's email was jeff.skilling@enron.com. 
*/
 
db.messages.aggregate([
    
      {"$unwind":"$headers.To"},
      {"$match":{"$or": [
					      {"headers.To" :"jeff.skilling@enron.com"},
					      {"headers.Bcc":"jeff.skilling@enron.com"},
					      {"headers.Cc" :"jeff.skilling@enron.com"}
					    ]
			    }
	  },
	  {"$group" : {   _id: { sender: "$headers.From"}, 
		            count: {"$sum": 1}
		           }
	  },
	  {"$match": {"_id.sender": "andrew.fastow@enron.com"}}
      
   ])       
   
