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