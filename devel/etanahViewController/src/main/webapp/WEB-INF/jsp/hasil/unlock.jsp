<%-- 
    Document   : unlock
    Created on : Jan 13, 2015, 10:54:25 PM
    Author     : ezat.amir
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>

<!--<script type="text/javascript">

    function unlock(val, val2) {
        alert("masok unlock");
   
        form = document.form1;
        var answer = confirm("adakah anda pasti untuk Hapus?");
        if (answer) {
            alert ("2");
            form.action = "${pageContext.request.contextPath}/unlock?hapus=&diKunci=' + val + ;
            form.submit();
        }
    }
</script>-->

<script type="text/javascript">

    function unlock(idKaunter, dikunci, idSiri) {
        
        document.location = '${pageContext.request.contextPath}/unlock?hapus&IdKaunter=' +
                idKaunter + '&idDikunci=' + dikunci + '&idSiri=' + idSiri;

    }

</script>

<s:messages/>
<s:errors/>
<div id="page_div">


    <s:form beanclass="etanah.view.stripes.hasil.UnlockActionBean" >
        <s:hidden name="idKaunter"/> 
        <s:hidden name="dikunci"/> 
        <fieldset class="aras1">

            <display:table style="width:100%" class="tablecloth" name="${actionBean.senarai}" 
                           pagesize="10" cellpadding="0" cellspacing="0" id="row" requestURI="/unlock">
                <display:column title="ID Siri" property="idSiri"></display:column>
                <display:column title="Daerah"  property="cawangan.daerah.nama"></display:column>
                <display:column title="ID Kaunter" property="idKaunter" ></display:column>
                <display:column title="Di Kunci" property="dikunci"></display:column>
                <display:column title="Tarikh Kunci & Masa" property="trhKunci" />
                <%--<display:column title="Hapus" ><center><img alt='Klik Untuk Padam' border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' 
                             onclick="unlock('${actionBean.idKaunter}', '${dikunci}', this.form);" onmouseover="this.style.cursor = 'pointer';"></center>

            </display:column>--%>
                <display:column title="Hapus" >
                    <center><img src="${pageContext.request.contextPath}/images/not_ok.gif" 
                                 name="hapus" onclick="unlock('${row.idKaunter}', '${row.dikunci}','${row.idSiri}')"
                                 alt="Tamatkan Sesi"/></center>
                    </display:column>



            </display:table>


        </fieldset>
    </s:form>



