<%-- 
    Document   : rekod_penghantaran2
    Created on : 22 Julai 2010, 2:22:00 PM
    Author     : abu.mansur
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">
    function blankValidation(){
        var result = true;
        if(($('#noDasar').val() == null || $('#noDasar').val() == "") && ($('#status').val() == null || $('#status').val() == "")){
            alert("Sila isi No. Rujukan Dasar dan Pilih Status terlebih dahulu.");
            $('#noDasar').focus();
            result = false;
        }else if($('#noDasar').val() == null || $('#noDasar').val() == ""){
            alert("Sila isi No. Rujukan Dasar terlebih dahulu.");
            $('#noDasar').focus();
            result = false;
        }else if($('#status').val() == null || $('#status').val() == ""){
            alert("Sila Pilih Status terlebih dahulu.");
            $('#status').focus();
            result = false;
        }
        return result;
    }

    function popup(id){
        window.open("${pageContext.request.contextPath}/hasil/papar_hakmilik_pihak?showForm&idHakmilik="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600,scrollbars=1");
    }

    function popupRekod(id){
        var dasar = $('#noDasar').val();
        var status= $('#status').val();
        window.open("${pageContext.request.contextPath}/hasil/rekod_penghantaran2?rekodHantar&idHakmilik="+id+"&noDasar="+dasar+"&kodStatus="+status, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1024,height=700,scrollbars=1,resizable=1");
    }

    function dateValidation(id,value){
        var vsplit = value.split('/');
        var fulldate = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];
        var today = new Date();
        var sdate = new Date(fulldate);
        if (sdate > today){
            alert("Tarikh yang dimasukkan tidak sesuai.");
            $('#'+id).val("");
        }
    }

    function refreshRekod(dasar, status){
        var url = '${pageContext.request.contextPath}/hasil/rekod_penghantaran2?reloadPage&noDasar='+dasar+'&kodStatus='+status;
        $.get(url,
        function(data){
            $('#rekod_penghantaran2').html(data);
        }, 'html');
    }

    function popupDasar(){
        <%--var f = document.notis;--%>
        var url = '${pageContext.request.contextPath}/hasil/notis?popupCarianDasar&darimana=rekod';
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=600,scrollbars=1");
    }

    function refreshBase(dasar){
        var q = $('#form1').serialize();
        var url = document.rekodBase.action + '?refreshBase&noDasar='+dasar;
        window.location = url+q;
    }

</script>
<div id="rekod">
<s:form name="rekodBase" beanclass="etanah.view.stripes.hasil.RekodPenghantaran2ActionBean" id="rekod_penghantaran2">
    <%--<s:hidden name="kodStatus2"/>--%>
    <s:messages/>
    <s:errors/>&nbsp;
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Kemasukan Rekod Penghantaran
            </legend>
            <p>
                <label><font color="red">*</font>No. Rujukan Dasar :</label>
                <s:text name="noDasar" id="noDasar" size="35" maxlength="30" onblur="this.value=this.value.toUpperCase();"/>
                <img alt='Klik Untuk Cari Dasar' border='0' height="16" width="16" src='${pageContext.request.contextPath}/pub/images/search-icon.png' class='rem'
                     id='carianDasar' onclick='popupDasar();return false;' onmouseover="this.style.cursor='pointer';" title="Carian Dasar.">
            </p>
            <p>
                <label><em>*</em>Pilih Status :</label>
                <s:select id="status" name="kodStatus" style="width:235px;">
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="${actionBean.senaraiKodNotis}" label="nama" value="kod"   />
                </s:select>
            </p>
            <table width="100%">
                <tr>
                    <td align="right">
                        <s:submit class="btn" onclick="return blankValidation();" name="search" value="Cari"/>&nbsp;
                        <s:button class="btn" name="reset" value="Isi Semula" onclick="clearText('rekod_penghantaran2');"/>
                    </td>
                </tr>
            </table>
        </fieldset>
        <br>
        <c:if test="${actionBean.visible}">
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Senarai Rekod Penghantaran
                    </legend>
                    <div class="content" align="center">

                        <display:table class="tablecloth" name="${actionBean.senaraiHakmilik}"
                                       pagesize="10" cellpadding="0" cellspacing="0" requestURI="/hasil/rekod_penghantaran2" id="line">
                            <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                            <display:column title="ID Hakmilik"><a href="#" onclick="popup('${line.hakmilik.idHakmilik}');return false;">${line.hakmilik.idHakmilik}</a></display:column>
                            <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                                <display:column property="hakmilik.akaunCukai.noAkaun" title="Nombor Akaun" class="${line_rowNum}"/>
                            </c:if>
                            <display:column property="noRujukan" title="No Fail" class="${line_rowNum}" />
                            <display:column title="Rekod Penghantaran">
                                <div align="center">
                                    <img alt='Klik Untuk Kemasukan' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                         id='rem_${line_rowNum}' onclick="popupRekod('${line.hakmilik.idHakmilik}');return false;" onmouseover="this.style.cursor='pointer';">
                                </div>
                            </display:column>
                            <display:column title="Status Kemasukan">
                                <div align="center">
                                    <c:set value="" var="status"/>
                                    <c:forEach items="${line.senaraiNotis}" var="senaraiNotis">
                                        <c:if test="${senaraiNotis.notis.kod eq actionBean.kodStatus and senaraiNotis.statusTerima ne null}">
                                            <c:set value="siap" var="status"/>
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${status eq ''}">
                                        <em>Belum diisi.</em>
                                    </c:if>
                                    <c:if test="${status ne ''}">
                                        Sudah diisi.
                                    </c:if>
                                </div>
                            </display:column>
                        </display:table>
                    </div>
                </fieldset>
                <c:if test="${actionBean.kodStatus eq 'SPN'}">
                    <center><em>Nota : </em>Surat Peringatan Cukai <em>TIDAK</em> perlu kemasukan rekod penghantaran.</center>
                </c:if>
            </div>
        </c:if>
    </div>
</s:form>
</div>
