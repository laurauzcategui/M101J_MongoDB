db.posts.aggregate(  [     {"$unwind":"$comments"},
                          
 {'$group':{_id:{author:"$comments.author"}, 'longest':{"$sum":1}}},
                            {"$sort": {"longest": -1 }}, 
                            {"$limit" : 1},
                            {"$project": { _id: 0, 'comment' : '$_id', 'longest':1}}
                       ]
                  )




