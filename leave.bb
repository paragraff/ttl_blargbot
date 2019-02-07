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