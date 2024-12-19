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
    $(document).ready(function(){
        document.getElementById("checked").checked = true;
        $('#loading-img').hide();
        $("#senarai_tugasan input:text").each( function(el) {
            $(this).blur( function() {
                $(this).val( $(this).val().toUpperCase());
            });
        });
        $("img[title]").tooltip({
            // tweak the position
            offset: [10, 2],

            // use the "slide" effect
            effect: 'slide'
        }).dynamic({ bottom: { direction: 'down', bounce: true } });

        $('a[href]').click(function(){
            $('#loading-img').show();
        });
        
    });

    function doAcquireAuto(t, m){
        $('#loading-img').show();
        var url = '${pageContext.request.contextPath}/linkActionBean?autoAcquire&idPermohonan=' + m + '&taskId=' + t;
        //alert(url);
        //f.action = url;
        var frm = document.form1;
        frm.action = url;
        //alert(frm.action);
        frm.submit();
    }

    function validateForm(){
        /*checking for N (Aliran Akhir) id:kod*/
           if($("#idInsert").val() == "" && $("#datepicker1").val() == "" && $("#datepicker").val() == ""){
            alert('Sila masukkan samada "ID Mohon" ATAU "Tarikh Dari" terlebih dahulu.');
            $("#idInsert").focus()&& $("#datepicker1").focus()&& $("#datepicker").focus();
            return false;
        }
        return true;
    }


</script>
<s:errors/>
<s:messages/>
<br>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif"
     width="50" height="50" style="display: none" alt=""/>
<s:form id="senarai_tugasan" beanclass="etanah.view.penguatkuasaan.SenaraiTugasanPenguatkuasaanActionBean" name="form1">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Carian</legend>
            <div class="content" align="center">
                <table>
                    <tr>
                        <td class="rowlabel1">ID Permohonan:</td>
                        <td class="input1"><s:text name="idInsert" id="idInsert"/> </td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Tarikh Dari :</td>
                        <td class="input1">
                            <s:text name="fromDate"  formatType="date" formatPattern="dd/MM/yyyy" id="datepicker1" class="datepicker"/>
                            <font size="1" color="red">[hh/bb/tttt]</font>
                        </td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Tarikh Hingga :</td>
                        <td class="input1">
                            <s:text name="untilDate"  formatType="date" formatPattern="dd/MM/yyyy" id="datepicker" class="datepicker"/>
                            <font size="1" color="red">[hh/bb/tttt]</font>
                        </td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Paparan :</td>
                        <td class="input1">
                            <s:radio name="checked" id="checked" value="N" checked="checked"></s:radio> Aliran Terakhir
                            <s:radio name="checked" value="Y"></s:radio> Semua Aliran
                            
                        </td>
                    </tr>
                    <tr><td class="rowlabel1"></td>
                        <td>
                            <s:button name="searchAllPermohonan2" value="Cari" class="btn" onclick="validateForm()"/>
                            <s:button  name="reset" value="Isi Semula" class="btn" onclick="clearText('senarai_tugasan');"/>
                        </td>
                    </tr>
                </table>

                <br>

            </div>
        </fieldset>
    </div>
    <br>

    <div class="subtitle">
    <fieldset class="aras1">
        <legend>Senarai Tugasan</legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiFasaPermohonan}"  id="line" pagesize="10" style="width:95%" requestURI="${pageContext.request.contextPath}/penguatkuasaan/senaraiTugasan">
                <display:column title="Bil" class="bil${line_rowNum}" style="${bcolor}">${line_rowNum}</display:column>
                <display:column title="ID Permohonan" class="idInsert{line_rowNum}" style="${bcolor}">
                        <a style="${bcolor}">${line.permohonan.idPermohonan}</a>
                    </display:column>
                    <display:column title="Urusan" style="${bcolor}">
                        <c:if test="${line.permohonan.status eq 'SS'}">
                            <img src="${pageContext.request.contextPath}/pub/images/icons/alertIcon.png" alt=""/>
                        </c:if>
                        <a style="${bcolor}">${line.permohonan.kodUrusan.nama}</a>
                    </display:column>
                    <display:column value="${line.idAliran}" title="Aliran Tugasan" style="${bcolor}"/>
                    <display:column title="Tarikh Masuk" style="${bcolor}"><fmt:formatDate pattern="dd/MM/yyyy hh:mm:ss" value="${line.infoAudit.tarikhMasuk}"/></display:column>
                    <display:column title="Tarikh Kemaskini" style="${bcolor}"><fmt:formatDate pattern="dd/MM/yyyy hh:mm:ss" value="${line.infoAudit.tarikhKemaskini}"/></display:column>
                <display:column title="Tarikh Hantar" style="${bcolor}"><fmt:formatDate pattern="dd/MM/yyyy hh:mm:ss" value="${line.tarikhHantar}"/></display:column>
                </display:table>
            </div>
    </fieldset>
</div>
</s:form>
<br>

