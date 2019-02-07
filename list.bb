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
