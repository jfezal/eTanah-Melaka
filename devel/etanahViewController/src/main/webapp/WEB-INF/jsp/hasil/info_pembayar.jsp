<%--
    Document   : info_pembayar
    Created on : Mei 21, 2010, 7:07:28 PM
    Author     : nurfaizati
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">
    function save1(f){
        if(validation()){

        }
        else{
            self.opener.refreshPage1(f);
            self.close();
        }
    }

</script>



<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.hasil.PertanyaanHakmilikActionBean" id= "pembayar">

     <br>
                    <div class="subtitle">
                        <fieldset class="aras1">
                            <legend>
                                Pembayar
                            </legend>
 
            <p><label>Nama :</label>
                ${actionBean.pihak.nama}&nbsp;

            </p>
            <p><label>No Pengenalan :</label>
                ${actionBean.pihak.noPengenalan}&nbsp;

            </p>
            <p><label>Jenis Pengenalan :</label>
                ${actionBean.pihak.jenisPengenalan.nama}&nbsp;

            </p>
              <%--     --%><%--         <div class="content" align="center">

                                <display:table class="tablecloth" name="${actionBean.listAkaun}"
                                               pagesize="10" cellpadding="0" cellspacing="0" requestURI="/hasil/pertanyaan_hakmilik" id="line">

                                    <display:column property="pemegang.nama" title="Nama Pembayar"  />
                                    <display:column  title="Nama Pembayar" >${line.pemegang.nama}</display:column>
                                    <display:column property="pemegang.jenisPengenalan.nama" title="Jenis Pengenalan"  />
                                    <display:column property="pemegang.noPengenalan" title="No Pengenalan"  />

                                </display:table>
                            </div>--%>
                        </fieldset>
                            </div>

</s:form>
