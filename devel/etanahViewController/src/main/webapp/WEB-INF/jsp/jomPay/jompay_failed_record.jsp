
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>





<s:errors/>
<s:messages/>
<body>
    <script type="text/javascript">
        $(document).ready(function() {



        });

        function toggle(source) {
            var checkboxes = document.querySelectorAll('input[type="checkbox"]');
            alert(checkboxes);
            for (var i = 0; i < checkboxes.length; i++) {
                if (checkboxes[i] !== source)
                    checkboxes[i].checked = source.checked;
            }
        }


        function simpan(event, f) {

            var url = f.action + '?' + event;
            $.post(url,
                    function() {
                        self.close();
                    }, 'html');
            setTimeout("self.close()", 1000);
        }

        function selectAll(a) {
            alert(a);
            var len = $('.checkbox').length;
            for (var i = 0; i < len; i++) {
                var c = document.getElementById("checkbox" + i);
                c.checked = a.checked;
            }
        }

    </script>

    <div id="error"/>
    <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>

    <s:form beanclass="etanah.view.stripes.jomPay.JomPayFailedActionBean" name="form">

        <s:errors/>
        <s:messages/>
        <fieldset class="aras1">
            <legend>Senarai Fail Berjaya Di Muatnaik</legend>
            <p>
                <%-- <display:table style="width:100%" class="tablecloth" name="${actionBean.listJompayDetails}"  cellpadding="0" cellspacing="0" id="line">  
                     <c:if test="${actionBean.edit eq 'true'}">
                          <display:column title="<input type='checkbox' name='semua' id='choose' onclick='javascript:selectAll(this);' /> Pilih">
                              <s:checkbox name="item" id="chkbox${line_rowNum}" value="${line.idJomPayDetails}" class="checkbox"/>
                          </display:column>
                      </c:if>
                      <c:if test="${actionBean.edit eq 'true'}">
                         <display:column title="Pilih"><s:checkbox name="item" value="${line.idJomPayDetails}" /></display:column>
                     </c:if>--%>
                <display:table style="width:100%" class="tablecloth" name="${actionBean.listFailed}" pagesize="100" cellpadding="0" cellspacing="0"
                               requestURI="/jomPay/details_transaction" id="line">
                    
                         <display:column title="Pilih"><s:checkbox name="item" value="${line.id}" /></display:column>

                    <display:column title="Bil">${line_rowNum}</display:column>
                    <display:column title="Akaun No">${line.jomPayFailDetails.akaunNo}<s:hidden name="ids" value="${line.jomPayFailDetails.idJomPayDetails}"/></display:column>
                    <display:column title="Rujukan">${line.jomPayFailDetails.rujukanBayaran}</display:column>
                    <display:column title="Id Hakmilik"><s:text name="idHakmilik">${line.hakmilik.idHakmilik} </s:text></display:column>
                    <display:column title="Rujukan 2">${line.jomPayFailDetails.rujukanDetails}</display:column>
                    <display:column title="Amaun">${line.jomPayFailDetails.transAmaun}</display:column>
                    <display:column title="Tarikh Transaksi">${line.jomPayFailDetails.tarikhTrans}</display:column>
                    <display:column title="Di Masuk Oleh">${line.infoAudit.dimasukOleh.nama}</display:column>
                    <c:if test="${line.jomPayFailDetails.status eq 'T'}">
                        <display:column title="Status">BELUM DI PROSES</display:column>
                    </c:if>
                </display:table>
            </p>
            <s:hidden name="idUploadFile"/>
<s:submit name="reconcile" value="Kemaskini" class="btn"/>
        <br/>
       
    </fieldset>
    <br/> 

    <br/> 
</s:form>
</body>

