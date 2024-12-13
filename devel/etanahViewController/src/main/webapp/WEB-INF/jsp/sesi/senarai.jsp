<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>

<script type="text/javascript">

function killSession(userId, sessionId){
	if (confirm('Adakah anda pasti untuk menamatkan sesi ' + userId + '? Kerja-kerja penguna ini yang '
			+ ' belum disimpan akan hilang!')){
		document.location = '${pageContext.request.contextPath}/sesi/senarai?killSession=&sessionToKill=' +
				sessionId;
	}
}

</script>

<s:messages/>
<s:errors/>

<s:form beanclass="etanah.view.session.SessionList" >
      <fieldset class="aras1">

          <display:table style="width:100%" class="tablecloth" name="${actionBean.sessionList}" 
                pagesize="10" cellpadding="0" cellspacing="0" id="row" requestURI="/sesi/senarai">
              <display:column title="ID Pengguna" >
                    ${row.userId} <img src="${pageContext.request.contextPath}/images/not_ok.gif" 
                            onclick="javascript:killSession('${row.userId}', '${row.sessionId}')" 
                            alt="Tamatkan Sesi"/>
              </display:column>
              <display:column title ="Tarikh Login">
                   <fmt:formatDate pattern="dd/MM/yyyy hh:mm:ss aa" value="${row.dateCreated}"/>
              </display:column>
              <display:column title="ID Sesi" property="sessionId" />
              <display:column title="IP" property="userIPAddr" />
              <display:column title="Komputer" property="computerName" />
          </display:table>
          

      </fieldset>
</s:form>
