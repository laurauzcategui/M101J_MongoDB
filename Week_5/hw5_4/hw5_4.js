/*
In this problem you will calculate the number of people who live in a zip code in the US 
where the city starts with a digit. We will take that to mean they don't really live in a city. 
Once again, you will be using the zip code collection, which you will find in the 'handouts' link in this page.

29,467 documents. 

The project operator can extract the first digit from any field. For example, to extract the first digit from the city field, you could write this query:
db.zips.aggregate([
    {$project: 
     {
	first_char: {$substr : ["$city",0,1]},
     }	 
   }
])
Using the aggregation framework, calculate the sum total of people who are living in a zip code where the city starts with a digit. Choose the answer below. 

Note that you will need to probably change your projection to send more info through than just that first character. 
Also, you will need a filtering step to get rid of all documents where the city does not start with a digital (0-9).

{$match: {$nor: [{ "city": {$regex: $or: [{/^1/},{/^2/}] }}] }},
*/

db.zips.aggregate([
    
    {$match : { $or : [  { city: {$regex: /^0/ }},
						 { city: {$regex:  /^1/ }},
						 { city: {$regex:  /^2/ }},
		                 { city: {$regex:  /^3/ }},
		                 { city: {$regex:  /^4/ }},
		                 { city: {$regex:  /^5/ }},	
						 { city: {$regex:  /^6/ }},
						 { city: {$regex:  /^7/ }},
		                 { city: {$regex:  /^8/ }},
		                 { city: {$regex:  /^9/ }}]}},
	{$group: { _id: null, pop: {$sum: "$pop"}}} 
	 
	
	])
	
    
    


