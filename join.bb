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