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
