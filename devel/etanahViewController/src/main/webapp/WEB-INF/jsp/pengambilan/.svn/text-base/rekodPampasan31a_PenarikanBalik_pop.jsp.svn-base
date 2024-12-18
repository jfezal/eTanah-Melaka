<%--
    Document   : rekodPenerimaanBayaranPampasan31a_pop
    Created on : Oct 11, 2010, 11:14:55 AM
    Author     : Rajesh
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>

<script type="text/javascript">


$(document).ready( function(){
$(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
});

        function save1(event, f){
            if(validation()){

            }
            else{
                var q = $(f).formSerialize();
                var url = f.action + '?' + event;
                $.post(url,q,
                function(data){
                    $('#page_div',opener.document).html(data);
                    self.close();
                },'html');
            }
        }
          $(document).ready(function() {
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
    });



  </script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<div class="subtitle">

         <s:form beanclass="etanah.view.stripes.pengambilan.RekodPampasan31a_PenarikanBalikActionBean" >
             <fieldset class="aras1">
            <legend>

            </legend>
                 <display:table class="tablecloth" name="${actionBean.senaraiAmbilPampasan}" pagesize="5" cellpadding="0" cellspacing="0"
                                requestURI="/pengambilan/bayaranPampasan31aPB" id="line">
                     <display:column title="No" sortable="true">${line_rowNum}</display:column>
                     <display:column title="Jumlah Pampasan Yang Diterima(RM)">
                         RM <fmt:formatNumber pattern="#,##0.00" value="${line.jumTerimaPampasan}"/>
                     </display:column>
                     <%--can use it 18052011--%>
                     <%--<display:column property="kodCaraBayaran.nama" title="Cara Pembayaran" />
                     <display:column property="noDok" title="Doc No."/>
                     <display:column title="Tarikh" >
                         <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhDok}" />
                     </display:column>
                     <display:column property="kodBank.nama" title="Bank" />--%>
                 </display:table>

            <p>
                <label>&nbsp;</label>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
        </fieldset>


         </s:form>
</div>