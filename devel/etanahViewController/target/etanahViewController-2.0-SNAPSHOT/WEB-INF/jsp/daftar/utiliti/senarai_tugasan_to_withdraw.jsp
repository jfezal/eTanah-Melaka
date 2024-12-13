<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.util.List;" %>
<link type="text/css" rel="stylesheet"
      href="${pageContext.request.contextPath}/pub/styles/jquery.tooltip.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.tools.min.js?select=full&debug=true"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">



    function hapus(form) {
        var len = $('.urusan').length;
        var param = '';

        for (var i = 1; i <= len; i++) {
            var ckd = $('#chkbox_pemohon_' + i).is(":checked");
            if (ckd) {
                param = param + '&idPermohonan=' + $('#chkbox_pemohon_' + i).val();
            }
        }
        if (param == '') {
            alert('Tiada urusan.');
            return;
        }
        form = document.form1;
        var answer = confirm("adakah anda pasti untuk Hapus?");
        if (answer) {
            form.action = "${pageContext.request.contextPath}/daftar/withdraw?withdrawTask" + param;
            form.submit();
        }
    }

    function selectAll() {
        var x = document.getElementsByName("checkbox");
        var xy = document.getElementsByName("semua");

        for (i = 0; i < x.length; i++)
        {
            x[i].checked = xy.checked;
            x[i].checked = true;
        }
    }

</script>
<s:errors/>
<s:messages/>
<br>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif"
     width="50" height="50" style="display: none" alt=""/>
<s:form  beanclass="etanah.view.daftar.utiliti.WithdrawTaskActionBean" name="form1">

    <br>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Senarai Tugasan To Withdraw
            </legend>
            <fieldset class="aras1">
                <div class="content" align="center">
                    <table>
                        <tr>
                            <td class="rowlabel1">ID Permohonan :</td>
                            <td class="input1"><s:text name="idInsert"/> </td>
                        </tr>             
                        <tr>
                            <td></td>
                            <td>
                                <s:submit name="setDefault" value="Cari" class="btn"/>
                            </td>
                        </tr>
                    </table>
                </div>
            </fieldset>
            <c:set var="row_num" value="${actionBean.__pg_start}"/>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.listValue}"
                               id="line" pagesize="10" style="width:95%"
                               requestURI="/daftar/withdraw"
                               requestURIcontext="true"
                               sort="external" size="${actionBean.__pg_total_records}" partialList="true">                    
                    <c:set var="row_num" value="${row_num+1}"/>
                    <c:set var="bcolor" value=""/>
                    <c:set var="title" value="ID Permohonan"/>
                    <c:if test="${line.red eq true}">
                        <c:set var="bcolor" value="color:red;"/>
                    </c:if>
                    <c:if test="${!empty registration}">
                        <c:set var="title" value="ID Perserahan"/>
                    </c:if>
                    <display:column title="<input type='checkbox' id='semua' name='semua' onclick='javascript:selectAll();' />">
                        <s:checkbox name="checkbox" id="chkbox_pemohon_${line_rowNum}" value="${line.idPermohonan}"/>
                    </display:column>
                    <display:column title="${title}" class="idPermohonan${line_rowNum}" style="${bcolor}">                        
                        <a style="${bcolor}">${line.idPermohonan}</a>                        
                    </display:column>
                    <display:column title="Urusan" style="${bcolor}" class="urusan">
                        <c:if test="${line.status eq 'SS'}">
                            <img src="${pageContext.request.contextPath}/pub/images/icons/alertIcon.png" alt=""/>
                        </c:if>
                        ${line.tajuk}
                        <s:hidden id="tajuk${row_rowNum - 1}" name="tajuk"/>
                    </display:column>
                    <display:column property="tindakan" title="Tindakan" style="${bcolor}"/>
                    <display:column property="tarikhMula" title="Tarikh Terima" style="${bcolor}"/>
                    <display:column property="tarikhTamat" title="Tarikh Sasaran" style="${bcolor}"/>
                </display:table>
            </div>
        </fieldset>            
        <br>
        <p>
            <label>&nbsp;</label>
            <s:button class="btn" value="Withdraw" name="withdraw" onclick="hapus(this.form);" onmouseover="this.style.cursor='pointer';"/>&nbsp;
        </p>


        <br>

    </div>
</s:form>
<br>

