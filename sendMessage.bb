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