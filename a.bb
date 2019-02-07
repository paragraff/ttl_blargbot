=======================================================
Invite blargbot(https://blargbot.xyz/) in a discord server.

Copy each code block between asterisks, paste it
in an any server channel and press enter.

After executing all of these commands rs queue bot will be ready.

Commands will be available:
!8, !9, !10 - join to a queue 8-10 lvl
!-8, !-9, !-10 - remove from a queue 8-10 lvl
!l8, !l9, !l10 - list players in a queue
!r - reset the afk remind timer

Remind time is 30m and can be changed.
User recieve remind message into DM. Time to reaction is 5m.
After remind time ended user will be removed from a queue.
=======================================================
=======================================================
init variables for the bot
*******************************************************
b!prefix add ! -d
*******************************************************
!cc create init {set;_rsLevels;rs8;rs9;rs10}
{set;_remindTime;30}
{set;_reactionRemindTime;5}
{set;_fullCount;4}
*******************************************************
!init
*******************************************************
=======================================================
=======================================================
create checker of arguments command and hide it
*******************************************************
!cc create q {if;{indexof;{get;_rsLevels};{args;0}};>;-1;
  {execcc;qjoin;{args;0}};
  {execcc;adm;Queue {args;0} is not exist{newline}}
}
*******************************************************
!cc hide q
*******************************************************
=======================================================
create alias for the join to the rs8 queue command
*******************************************************
!cc create 8 {execcc;q;rs8}
*******************************************************
!cc sethelp 8 add you to the rs8 queue
*******************************************************
=======================================================
create alias for the join to the rs9 queue command
*******************************************************
!cc create 9 {execcc;q;rs9}
*******************************************************
!cc sethelp 9 add you to the rs9 queue
*******************************************************
=======================================================
create alias for the join to the rs10 queue command
*******************************************************
!cc create 10 {execcc;q;rs10}
*******************************************************
!cc sethelp 10 add you to the rs10 queue
*******************************************************
=======================================================
create alias for the list of the rs8 queue command
*******************************************************
!cc create l8 {execcc;qlist;rs8}
*******************************************************
!cc sethelp l8 show the rs8 queue
*******************************************************
=======================================================
create alias for the list of the rs9 queue command
*******************************************************
!cc create l9 {execcc;qlist;rs9}
*******************************************************
!cc sethelp l9 show the rs9 queue
*******************************************************
=======================================================
create alias for the list of the rs10 queue command
*******************************************************
!cc create l10 {execcc;qlist;rs10}
*******************************************************
!cc sethelp l10 show the rs10 queue
*******************************************************
=======================================================
create alias for the remove from the rs8 queue command
*******************************************************
!cc create -8 {execcc;qleave;rs8}
*******************************************************
!cc sethelp -8 remove user from the rs8 queue. You can specify user to remove in the first argument: `!-8 username`
*******************************************************
=======================================================
create alias for the remove from the rs9 queue command
*******************************************************
!cc create -9 {execcc;qleave;rs9}
*******************************************************
!cc sethelp -9 remove user from the rs9 queue. You can specify user to remove in the first argument: `!-9 username`
*******************************************************
=======================================================
create alias for the remove from the rs10 queue command
*******************************************************
!cc create -10 {execcc;qleave;rs10}
*******************************************************
!cc sethelp -10 remove user from the rs10 queue. You can specify user to remove in the first argument: `!-10 username`
*******************************************************
=======================================================
=======================================================
create command to reset reminder timer
*******************************************************
!cc create r {delete}
{set;_{userid}Status;true}{commit}
{timer;{set;_{userid}Status;false}{commit};{get;_reactionRemindTime}m}
{timer;{dm;{userid};Confirm your ready status for a rs run, type `!r` in the {zws}#rs_missions {zws}channel};{get;_remindTime}m}
  {timer;
    {if;{get;_{userid}Status};==;false;
      {execcc;-}{set;_{userid}Status}
    };
    {math;+;{get;_remindTime};{get;_reactionRemindTime}}m
  }
*******************************************************
!cc sethelp r Reset the afk remind timer. If you don't reset it in time you will be removed from a queue
*******************************************************
=======================================================
=======================================================
create command which join user to a queue,
create reminder timers,
check count of users in a queue,
send notify when queue is full
*******************************************************
!cc create qjoin {delete}
{if;{logic;&&;{isarray;{get;_{args;0}}};{bool;>;{length;{get;_{args;0}}};0}};==;true;
  {if;{get;_{args;0}};includes;{userid};
    {execcc;
      adm;{usermention}, you have been added to the queue for {args;0}. You will be pinged when all members are queued
    }{execcc;
      qlist;
      {args;0}
    };
    {push;
      !_{args;0};{userid}
    }{if;{length;{get;_{args;0}}};<;{math;-;{get;_fullCount};1};
       {execcc;
         adm;{usermention}, you have been added to the queue for {args;0}. You will be pinged when all members are queued
       }{execcc;
         qlist;
         {args;0}
       }
    }{if;{length;{get;_{args;0}}};==;{math;-;{get;_fullCount};1};
      {execcc;
        adm;{usermention}, you have been added to the queue for {args;0}. You will be pinged when all members are queued
      }{execcc;
        qlist;
        {args;0}
      }
      {rolemention;{args;0}} there are currently {math;-;{get;_fullCount};1} people queued for {args;0}.
    }{if;{length;{get;_{args;0}}};==;{get;_fullCount};
       {foreach;~user;_{args;0};
         {usermention;{get;~user}},
       } your queue for rs8 is now complete.{if;{length;{get;_{args;0}Message}};!=;0;
         {delete;{channelid};{get;_{args;0}Message}}{set;!_{args;0}Message}
       }{set;!_{args;0};[]}
    }
  };
  {set;
    _{args;0};
    ["{userid}"]
  }{commit}{execcc;
    adm;{usermention}, you have been added to the queue for {args;0}. You will be pinged when all members are queued
  }{execcc;
    qlist;
    {args;0}
  }
}
{if;{get;_{userid}Status};==;;
  {timer;{dm;{userid};Confirm your ready status for a rs run, type `!r` in the {zws}#rs_missions {zws} channel};{get;_remindTime}m}
  {timer;
    {if;{get;_{userid}Status};==;false;
      {execcc;-}{set;_{userid}Status}
    };
    {math;+;{get;_remindTime};{get;_reactionRemindTime}}m
  }
}
{set;_{userid}Status;false}{commit}
*******************************************************
!cc hide qjoin
*******************************************************
=======================================================
=======================================================
create command to remove user from a queue
*******************************************************
!cc create qleave {delete}
{if;{args;1};!=;;
  {set;~user;{userid}};
  {set;~user;{userid}}
}
{if;{isarray;{get;_{args;0}}};==;true;
  {if;{get;_{args;0}};includes;{get;~user};
    {set;
      !_{args;0};
      {filter;
        ~element;
        _{args;0};
        {bool;{get;~element};!=;{get;~user}}
      }
    }{execcc;
      adm;{usernick;{get;~user}} have been removed from the {args;0} queue
    }{execcc;qlist;{args;0}};
    {execcc;adm;{usermention}, you aren't in the {args;0} queue}
  }
}
*******************************************************
!cc hide qleave
*******************************************************
=======================================================
=======================================================
create command to show a queue
*******************************************************
!cc create qlist {delete}
{if;{length;{get;_{args;0}Message}};!=;0;
  {delete;{channelid};{get;_{args;0}Message}}
}
{set;
  _{args;0}Message;
{send;
  {channelid};
```
======================
  {if;{length;{get;_{args;0}}};<;1;
    {args;0} queue is empty now;
    {args;0} queue is:{newline}{for;
      ~index;
      0;
      <;
      {length;{get;_{args;0}}};
      {math;+;{get;~index};1}. {username;{get;_{args;0};{get;~index}}}{newline}
    }
  }
======================
```
}
}
*******************************************************
!cc hide qlist
*******************************************************
=======================================================
=======================================================
create command to remove user from all queues
*******************************************************
!cc create - {delete}

{if;{args;0};!==;;

  {set;~usernicks;[]
  }{set;~users;{guildmembers}
  }{foreach;~user;~users;
    {push;~usernicks;{usernick;{get;~user}}}
  }{set;~userToDelete;{userid}
  };

  {set;~userToDelete;{userid}}
}

{foreach;~rsQueueName;_rsLevels;
  {set;~queue;{get;_{get;~rsQueueName}}}
  {if;{indexof;{get;~queue};{get;~userToDelete}};>;-1;
    {execcc;qleave;{get;~rsQueueName};{get;~userToDelete}}
  }
}
*******************************************************
!cc sethelp - remove user from the all queues. You can specify user to remove in the first argument: `!- username`
*******************************************************
=======================================================
=======================================================
create command to autoremove messages
*******************************************************
!cc create adm {set;
  ~messageToDelete;
  {send;{channelid};{args}}
}
{timer;{delete;{channelid};{get;~messageToDelete}};15s}
*******************************************************
!cc hide adm
*******************************************************
=======================================================