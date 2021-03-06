/*

{
	"_id" : "92278",
	"city" : "TWENTYNINE PALMS",
	"loc" : [
		-116.06041,
		34.237969
	],
	"pop" : 11412,
	"state" : "CA"
}

Please calculate the average population of cities in California (abbreviation CA) and New York (NY) (taken together) with populations over 25,000. 

For this problem, assume that a city name that appears in more than one state represents two separate cities. 

Please round the answer to a whole number. 
Hint: The answer for CT and NJ (using this data set) is 38177. 

Please note:
Different states might have the same city name.
A city might have multiple zip codes.
* 
* 
* db.zips.aggregate([
   

  {$match:{$or:[
	{state:'CT',pop:{$gt:25000}},
	{state:'NJ',pop:{$gt:25000}}
   ]}},
   {$sort: {state: -1, city: 1 }},
   {$group: { _id: {city: "$city", state: "$state"} , population: {$sum:"$pop"}} },
   {$sort: {"_id.state": 1 }},
   {$group: { _id: null, population: {$avg: "$population"}}}

])
* 
*/

db.zips.aggregate([
   

  {$match:{$or:[
	{state:'CT'},
	{state:'NJ'}
   ]}},
   {$sort: {state: 1, city: 1 }},
   {$group: { _id: {city: "$city", state: "$state"} , population: {$sum:"$pop"}} },
   {$match : {population: {$gt:25000}}},
   {$sort: {"_id.state": 1 }},
   {$group: { _id: null, population: {$avg: "$population"}}}

])

