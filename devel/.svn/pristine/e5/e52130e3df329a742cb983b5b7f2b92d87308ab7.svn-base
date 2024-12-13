<%-- 
    Document   : kemasukan_pb_jual_danaharta
    Created on : Apr 12, 2010, 9:56:29 AM
    Author     : fikri
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
     function selectAll(a){
	for (i = 1; i < 100; i++){
            var c;
            c = document.getElementById("ids" + i);
            if (c == null) break;
            c.checked = a.checked;
	}
    }
    
    function remove(val){
        if(confirm ('Adakah Anda Pasti?')) {
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
            var url = '${pageContext.request.contextPath}/daftar/perintah_jual_pb?rmUrusanPajakanWithPihak';
            $.get(url,
            function(data){
                $('#page_div').html(data);
                $.unblockUI();
            },'html');
        }
    }

    function addNewPihak(){
            window.open("${pageContext.request.contextPath}/pihak_berkepentingan?pihakKepentinganPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=700,scrollbars=yes");
        }
</script>

<div class="subtitle displaytag">
    <s:errors/>
    <s:messages/>
    <s:form beanclass="etanah.view.daftar.PerintahJualPihakBerkepentingan">        
       <%-- <fieldset class="aras1">
            <legend>Senarai Pilihan Urusan Pajakan</legend>
            <div class="content" align="center">
                <p align="center"><label></label>
                    <display:table class="tablecloth" name="${actionBean.senaraiUrusanPilihan}" cellpadding="0" cellspacing="0" id="line1"
                                   requestURI="${pageContext.request.contextPath}/daftar/perintah_jual_pb">                        
                        <display:column title="Bil" sortable="true">${line1_rowNum}</display:column>
                        <display:column title="Urusan" property="urusan.kodUrusan.nama"/>
                        <display:column title="ID Perserahan" property="permohonan.idPermohonan"/>                        
                    </display:table>
                </p>
                <br/>                
            </div>
        </fieldset>--%>
        <br/>
        <fieldset class="aras1">
            <legend>Senarai Pihak Berkepentingan</legend>
            <div class="content" align="center">
                <p align="center"><label></label>
                    <display:table class="tablecloth" name="${actionBean.senaraiPB}" cellpadding="0" cellspacing="0" id="line2"
                                   requestURI="${pageContext.request.contextPath}/daftar/perintah_jual_pb">                        
                        <display:column title="Bil" sortable="true">${line2_rowNum}</display:column>
                        <display:column title="Nama" property="pihak.nama"/>
                        <display:column title="Jenis Pihak" property="jenis.nama"/>
                    </display:table>
                </p>
            </div>
        </fieldset>
        <br/>
        <fieldset class="aras1">
            <legend>Senarai Pihak Berkepentingan Baru</legend>
            <div class="content" align="center">
                <p align="center"><label></label>
                    <display:table class="tablecloth" name="${actionBean.senaraiMP}" cellpadding="0" cellspacing="0" id="line3"
                                   requestURI="${pageContext.request.contextPath}/daftar/perintah_jual_pb">
                        <display:column title="<input type='checkbox' name='semua' onclick='javascript:selectAll(this);' />">
                            <s:checkbox name="ids" id="ids${line3_rowNum}" value="${line3.idPermohonanPihak}" class="checkbox"/>
                        </display:column>
                        <display:column title="Bil" sortable="true">${line3_rowNum}</display:column>
                        <display:column title="Nama" property="pihak.nama"/>
                        <display:column title="Jenis Pihak" property="jenis.nama"/>
                    </display:table>
                </p>
                <p>
                    <label></label>
                    <s:button class="btn" value="Tambah Baru" name="new" id="new" onclick="addNewPihak();"/>&nbsp;
                    <c:if test="${fn:length(actionBean.senaraiMP)>0}">
                        <s:button class="btn" value="Padam" name="rmPermohonanPihak"
                                  id="rm" onclick="if(confirm('Adakah anda pasti?'))doSubmit(this.form, this.name,'page_div');"/>&nbsp;
                    </c:if>
                </p>
            </div>
        </fieldset>
    </s:form>
</div>
