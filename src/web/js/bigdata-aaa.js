function trim(string) {
  var i=0; j=0;
  for(i = 0; i<string.length && string.charAt(i)==" "; i++);
  for(j = string.length; j>0 && string.charAt(j-1)==" "; j--);
  if(i>j) return  "";  
  return  string.substring(i,j);  
}

function sort(list,attr){
  for (var i=0; i<list.length; i++){
    for (var j=i+1; j<list.length; j++){
      if (list[i][attr]>list[j][attr]){
        var t=list[i];
        list[i]=list[j];
        list[j]=t;
      }
    }
  }
}

function req() {
  var username=trim(document.getElementById('username').value);
  var password=trim(document.getElementById('password').value);
  var password1=trim(document.getElementById('password1').value);
  var dispname=trim(document.getElementById('dispname').value);
  if (username==""){
    alert("注册邮箱不能为空");
    return;
  }
  if (password==""){
    alert("密码不能为空");
    return;
  }
  if (password!=password1){
    alert("两次输入的密码不一致");
    return;
  }
  if (dispname==""){
    alert("用户名不能为空");
    return;
  }
  
  $.ajax({
      url: '/Reg.action',
      type: 'POST',
      data: 'username='+username+'&password='+password+'&dispname='+dispname,
      success: function(m){
          if (m.result=="OK") {
            window.location.href="reg2.jsp";
          } else {
            alert('注册用户失败 : '+m.message);
          }
      },
      error: function(){
          alert('注册用户失败');
      }
  });
}

function changepwd() {
  var oldpassword=trim(document.getElementById('oldpassword').value);
  var newpassword=trim(document.getElementById('newpassword').value);
  var newpassword2=trim(document.getElementById('newpassword2').value);
  if (oldpassword==""){
    alert("旧密码不能为空");
    return;
  }
  if (newpassword==""){
    alert("新密码不能为空");
    return;
  }
  if (newpassword!=newpassword2){
    alert("两次输入的新密码不一致");
    return;
  }
  
  $.ajax({
      url: '/user/ChangePwd.action',
      type: 'POST',
      data: 'newpassword='+newpassword+'&oldpassword='+oldpassword,
      success: function(m){
          if (m.result=="OK") {
            alert('修改密码成功');
          } else {
            alert('修改密码失败 : '+m.message);
          }
      },
      error: function(){
          alert('修改密码失败');
      }
  });
}

function listAccessKey() {
  document.getElementById("list").innerHTML="正在载入访问凭证列表...";
  document.getElementById("newAccessKeyButton").disabled=false;
  document.getElementById("activateAccessKeyButton").disabled=true;
  document.getElementById("deactivateAccessKeyButton").disabled=true;
  document.getElementById("delAccessKeyButton").disabled=true;
  document.getElementById("viewAccessKeyButton").disabled=true;
  $.ajax({
      url: '/user/ListAccessKey.action',
      type: 'POST',
      success: function(m){
          if (m.result=="OK") {
        	  accessKeyList=m.accessKeyList;
      		  selectIndex=-1;
        	  if (accessKeyList.length==0) {
        	    document.getElementById("list").innerHTML="访问凭证列表无数据";
        	  } else {
        	    sort(accessKeyList, "createTime");
        	    var view = "<table class='STYLE2'>";
        	    view += "<tr><td width='200'>访问凭证</td><td width='200'>创建时间</td><td width='100'>状态</td><td width='10'></td></tr>";
                for (var i=0; i<accessKeyList.length; i++) {
                  view += "<tr><td width='200'>"+accessKeyList[i].accessKey+"</td>";
                  view += "<td width='300'>"+new Date(accessKeyList[i].createTime)+"</td>";
                  view += "<td width='100'>"+(accessKeyList[i].status==0?"有效":"失效")+"</td>";
                  view += "<td width='10'><input type='radio' name='index' value='"+i+"' onClick='javascript:selectAccessKey("+i+")'></td></tr>";
                }
        	    view += "</table>";
                document.getElementById("list").innerHTML=view;
        	  }
          } else {
            document.getElementById("list").innerHTML='载入访问凭证列表失败 : '+m.message;
          }
      },
      error: function(){
    	  document.getElementById("list").innerHTML='载入访问凭证列表失败';
      }
  });
}

function selectAccessKey(index){
  selectIndex = index;
  if (accessKeyList[index].status==0) {
    document.getElementById("activateAccessKeyButton").disabled=true;
    document.getElementById("deactivateAccessKeyButton").disabled=false;
  } else {
    document.getElementById("activateAccessKeyButton").disabled=false;
    document.getElementById("deactivateAccessKeyButton").disabled=true;
  }
  document.getElementById("viewAccessKeyButton").disabled=false;
  document.getElementById("delAccessKeyButton").disabled=false;
}

function newAccessKey() {
  $.ajax({
      url: '/user/NewAccessKey.action',
      type: 'POST',
      success: function(m){
          if (m.result=="OK") {
        	  listAccessKey();
          } else {
            alert('创建访问密钥失败 : '+m.message);
          }
      },
      error: function(){
          alert('创建访问密钥失败');
      }
  });
}

function viewAccessKey() {
  alert("安全密钥："+accessKeyList[selectIndex].securityKey);
}

function activateAccessKey(){
  $.ajax({
      url: '/user/ActivateAccessKey.action',
      type: 'POST',
      data: 'accesskey='+accessKeyList[selectIndex].accessKey,
      success: function(m){
          if (m.result=="OK") {
        	  listAccessKey();
          } else {
            alert('激活访问凭证失败: '+m.message);
          }
      },
      error: function(){
          alert('激活访问凭证失败');
      }
  });
}

function deactivateAccessKey(){
  $.ajax({
      url: '/user/DeactivateAccessKey.action',
      type: 'POST',
      data: 'accesskey='+accessKeyList[selectIndex].accessKey,
      success: function(m){
          if (m.result=="OK") {
        	  listAccessKey();
          } else {
            alert('去激活访问凭证失败: '+m.message);
          }
      },
      error: function(){
          alert('去激活访问凭证失败');
      }
  });
}

function delAccessKey() {
  $.ajax({
      url: '/user/DelAccessKey.action',
      type: 'POST',
      data: 'accesskey='+accessKeyList[selectIndex].accessKey,
      success: function(m){
          if (m.result=="OK") {
        	  listAccessKey();
          } else {
            alert('删除访问凭证失败: '+m.message);
          }
      },
      error: function(){
          alert('删除访问凭证失败');
      }
  });
}