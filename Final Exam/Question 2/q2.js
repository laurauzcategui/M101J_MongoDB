/*
For this question you will use the aggregation framework to figure out pairs of people that tend to communicate a lot.
*  To do this, you will need to unwind the To list for each message. 

This problem is a little tricky because a recipient may appear more than once in the To list for a message.
*  You will need to fix that in a stage of the aggregation before doing your grouping and counting of (sender, recipient) pairs. 
*/
 
db.messages.aggregate([
    
      {"$unwind":"$headers.To"},
      {"$group" : { _id : { oid: "$_id", sender : "$headers.From"}, 
		           recipient: {"$addToSet": "$headers.To"}
	              }
	  },
	  {"$unwind": "$recipient"},
	  {"$group": { _id: {sender: "$_id.sender", recipient: "$recipient"},
		                count : {"$sum": 1}
		         } 
	  }, 
	  {"$sort": {"count":-1}}

    ])  
      
