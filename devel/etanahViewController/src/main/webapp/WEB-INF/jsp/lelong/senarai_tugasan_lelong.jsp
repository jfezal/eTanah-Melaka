<%-- 
    Document   : senarai_tugasan_lelong
    Created on : Feb 14, 2013, 5:35:09 PM
    Author     : mazurahayati.yusop
--%>

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
        $('#loading-img').hide();
        $("#senaraiTugasanL input:text").each( function(el) {
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

    function doSelesai(id, taskId){
        $('#loading-img').show();
        var frm = document.form1;
        var msg;
        //frm.action = frm.action + '?' + event;
        if(confirm('Adakah anda pasti untuk selesaikan tugasan ini?')){
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
            var url = '${pageContext.request.contextPath}/stage?nextStage&idPermohonan=' + id + '&taskId=' + taskId + '&event=APPROVE';
            $.ajax({
                type:"GET",
                url : url,
                dataType : 'html',
                error : function(xhr, ajaxOptions, thrownError) {
                    //alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');
                    msg = xhr.statusText + '<br/>'+id+' : Tugasan tidak dapat dihantar ke peringkat seterusnya. Sila hubungi Pentadbir Sistem';
                    frm.action = '${pageContext.request.contextPath}/senaraiTugasan?error=' + msg;
                    frm.submit();
                },
                success : function(data) {
                    if(data == '1'){
                        msg = 'Tugasan tidak dapat dihantar ke peringkat seterusnya. Sila hubungi Pentadbir Sistem';
                        //frm.action = '${pageContext.request.contextPath}/senaraiTugasan?error=' + msg;
                        //frm.submit();
                        $('#loading-img').hide();
                        $.unblockUI();
                    }else if(data == '2'){
                        alert('Sila buat keputusan terlebih dahulu.');
                        //$('#status').click();
                        $('#loading-img').hide();
                        $.unblockUI();
                    }else if(data == '3') {
                        msg = 'Terdapat urusan sebelum yang masih belum selesai.';
                        //frm.action = '${pageContext.request.contextPath}/senaraiTugasan?error=' + msg;
                        //frm.submit();
                        $('#loading-img').hide();
                        $.unblockUI();
                    }else{
                        frm.action = '${pageContext.request.contextPath}/' + data;
                        frm.submit();
                    }
                }
            });
        }
    }

</script>
<s:errors/>
<s:messages/>
<br>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif"
     width="50" height="50" style="display: none" alt=""/>
<s:form id="senarai_tugasan" beanclass="etanah.view.stripes.lelong.SenaraiTugasanLelongActionBean" name="form1">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Carian
            </legend>
            <div class="content" align="center">


                <table>
                    <tr>
                        <td class="rowlabel1">ID Permohonan / ID Perserahan :</td>
                        <td class="input1"><s:text name="idInsert"/> </td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">&nbsp;</td>
                        <td ><font color="red">ATAU</font></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Tarikh Dari :</td>
                        <td class="input1">
                            <s:text name="fromDate" id="datepicker" class="datepicker"
                                    onblur="editDateBlur(this, 'DD/MM/YYYY')"
                                    onkeypress="return editDatePre(this, 'DD/MM/YYYY', event)"
                                    onkeyup="return editDate(this, 'DD/MM/YYYY', event);"/> <font size="1" color="red">[hh/bb/tttt]</font>
                        </td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Tarikh Hingga :</td>
                        <td class="input1">
                            <s:text name="untilDate" id="datepicker2" class="datepicker"
                                    onblur="editDateBlur(this, 'DD/MM/YYYY')"
                                    onkeypress="return editDatePre(this, 'DD/MM/YYYY', event)"
                                    onkeyup="return editDate(this, 'DD/MM/YYYY', event);"/> <font size="1" color="red">[hh/bb/tttt]</font>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <s:submit name="searchAllPermohonan" value="Cari" class="btn"/>

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
            <legend>
                Senarai Tugasan
            </legend>
            <br/>
            <c:if test="${fn:length(actionBean.listValue) > 0}">
                <font size="2" color="black">Petunjuk :</font>
                <p>
                    <img src="${pageContext.request.contextPath}/pub/images/icons/Forward.png"
                         width="20" height="20" /> - <font size="2" color="black">Untuk Terus mengambil Tugasan</font>
                    &nbsp;<b>|</b>&nbsp;
                    <img src="${pageContext.request.contextPath}/pub/images/icons/Finish.png"
                                 width="20" height="20"/> - <font size="2" color="black">Untuk Selesaikan Tugasan Tanpa Perlu Melihat Urusan Secara Terperinci</font>
                    &nbsp;<b>|</b>&nbsp;
                    <img src="${pageContext.request.contextPath}/pub/images/icons/alertIcon.png"
                                 width="20" height="20"/> - <font size="2" color="black">Tugasan yang telah dihantar kembali.</font>

                </p>

            </c:if>
            <c:set var="row_num" value="${actionBean.__pg_start}"/>
            <div class="content" align="center"><img src="${pageContext.request.contextPath}/pub/images/loading_img.gif" id="loading-img"/>&nbsp;
                <display:table class="tablecloth" name="${actionBean.listValue}"
                               id="line" pagesize="10" style="width:95%"
                               requestURI="senaraiTugasanL"
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
                    <display:column title="Bil" class="bil${line_rowNum}" style="${bcolor}">
                        <c:if test="${line.proceed}">
                            <img src="${pageContext.request.contextPath}/pub/images/icons/Forward.png"
                                 onclick="doAcquireAuto('${line.taskId}', '${line.idPermohonan}');"
                                 onmouseover="this.style.cursor='pointer';"
                                 width="20" height="20" title="Terus"/>
                        </c:if>
                        ${row_num}
                    </display:column>
                    <display:column title="${title}" class="idPermohonan${line_rowNum}" style="${bcolor}">
                        <a href="${line.link}" style="${bcolor}">${line.idPermohonan}</a>
                    </display:column>
                    <display:column title="Urusan" style="${bcolor}">
                        <c:if test="${line.status eq 'SS'}">
                            <img src="${pageContext.request.contextPath}/pub/images/icons/alertIcon.png" alt=""/>
                        </c:if>
                        <a href="${line.link}" style="${bcolor}">${line.tajuk}</a>
                         <c:if test="${!empty line.idPermohonanSebelum}">
                            <br/><font color="black">( Integrasi : ${line.idPermohonanSebelum} )</font>
                        </c:if>
                    </display:column>
                    <display:column property="tindakan" title="Tindakan" style="${bcolor}"/>
                    <display:column property="tarikhMula" title="Tarikh Terima" style="${bcolor}"/>
                    <display:column property="tarikhTamat" title="Tarikh Sasaran" style="${bcolor}"/>
                    <display:column title="Bil Hari" property="bil" style="${bcolor}"/>
                    <display:column title="Selesai">
                        <c:if test="${line.documentGenerate}">
                            <%--<s:button name="" value="Selesai" class="btn"/>--%>
                            <img src="${pageContext.request.contextPath}/pub/images/icons/Finish.png"
                                 alt=""
                                 onclick="doSelesai('${line.idPermohonan}','${line.taskId}');"
                                 onmouseover="this.style.cursor='pointer';"
                                 width="20" height="20" title="Selesai : ${line.idPermohonan}"/>
                        </c:if>
                    </display:column>
                </display:table>
            </div>
        </fieldset>

    </div>
</s:form>
<br>


