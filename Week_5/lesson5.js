// Week 5 

 // $match 
  
 db.zips.aggregate([{$match:
                     {
                       state : "CA" 
                      }
                    }
                  ]);

 db.zips.aggregate([{$match:
                     {
                       state : "CA" 
                      }
                    },
                   {$group : 
		            { 
		              _id: "city", 
		             population: {$sum : "$pop"},
                             zip_codes: {$addToSet : "$_id"} 
		            }
                    }
                  ]);


 db.zips.aggregate([{$match:
                     {
                       pop : { $gt : 100000} 
                      }
                    }
                  ])


// $sort

/*
 disk  & memory 
 before or after grouping


*/

 db.zips.aggregate([{$match:
                     {
                       state : "CA" 
                      }
                    },
                   {$group : 
		            { 
		              _id: "city", 
		             population: {$sum : "$pop"}
		            }
                   },
                   {
		    $project: 
                     {
                      _id: 0, 
		     city: "$_id", 
		     population: 1
                     }
 	           },
                   {
                    $sort: { population: -1}
                   }
 ]);

db.zips.aggregate (
[ {$sort: {
    state:1, city:1 
  }}
]
)

// $skip $limit 

  db.zips.aggregate([{$match:
                     {
                       state : "CA" 
                      }
                    },
                   {$group : 
		            { 
		              _id: "city", 
		             population: {$sum : "$pop"}
		            }
                   },
                   {
		    $project: 
                     {
                      _id: 0, 
		     city: "$_id", 
		     population: 1
                     }
 	           },
                   {
                    $sort: { population: -1}
                   },
                  {$skip: 10},
                  {$limit: 5}
 ]);


// $first $last 
// first has to be ordered


db.zips.aggregate(
 { 
 $group : {
  _id: {state: "$state", city: "$city"},
  population : { $sum : "$pop"}, 
 } 
 },
  { $sort: {"$_id.state": 1, "population":-1}},
  { $group : { _id: $_id.state, 
               city: {$first: "_id.city"}, 
               population: {$first : $population}
              }
   }
 
 
 }
)

