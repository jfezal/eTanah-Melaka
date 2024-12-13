<%-- 
    Document   : maklumat_Kehadiran
    Created on : Dec 24, 2010, 11:39:42 AM
    Author     : muhammad.amin
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">
    $(document).ready( function(){

    <%--        var rowNo = $('table#line tr').length;

        for (var i = 0; i < rowNo-1; i++){

            if($('#btnY'+i).is(":checked")){
                $('#wakil'+i).hide();
            }
            else if($('#btnN'+i).is(":checked")){
                $('#wakil'+i).show();
            }
            else {
                $('#wakil'+i).hide();
            }
        }--%>
            });

    <%--    function changeHadir(value,row){

        if(value == "N"){
            $('#wakil'+row).show();
        }else{
            $('#wakil'+row).hide();
        }
    }--%>


        function addWakil(id){
            var rowNo = $('table#line tr').length;
            var hadir;
            var catatan;

            for (var i = 0; i < rowNo-1; i++){

                if($('#btnY'+i).is(":checked")){
                    hadir = 'Y';
                }
                else if($('#btnN'+i).is(":checked")){
                    hadir = 'N';
                }
                        
                catatan = $('#catatan'+i).val();

                var url = '${pageContext.request.contextPath}/consent/maklumat_kehadiran?simpanBicaraHadir&hadir='+hadir+'&catatan='+catatan+'&row='+i;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }
            window.open("${pageContext.request.contextPath}/consent/maklumat_kehadiran?addWakil&idKehadiran="+id, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=670,scrollbars=yes");
        }
        
        function addKehadiran(){
            window.open("${pageContext.request.contextPath}/consent/maklumat_kehadiran?addKehadiranPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=670,scrollbars=yes");
        }

        function viewWakil(id){
            window.open("${pageContext.request.contextPath}/consent/maklumat_kehadiran?viewWakil&idKehadiran="+id, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=670,scrollbars=yes");
        }

        function removeKehadiran() {

            var param = '';
            if(confirm('Adakah anda pasti?')) {

                $('.remove').each(function(index){
                    var a = $('#rmv_hadir'+index).is(":checked");
                    if(a) {
                        param = param + '&idKehadiran=' + $('#rmv_hadir'+index).val();
                    }
                });
                if(param == ''){
                    alert('Sila Pilih Maklumat Terlebih Dahulu.');
                    return;
                }

                var url = '${pageContext.request.contextPath}/consent/maklumat_kehadiran?deleteMultipleKehadiran'+param;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                }, 'html');
            }
        }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.consent.MaklumatKehadiranActionBean" id="kehadiran">
    <s:messages/>
    <s:errors/>
    <div class="subtitle" align="center">
        <fieldset class="aras1">
            <legend>
                Senarai Kehadiran
            </legend>
            <br/>
            <display:table class="tablecloth" name="${actionBean.listPerbicaraanKehadiran}"  cellpadding="0" cellspacing="0" requestURI="/consent/maklumat_kehadiran" id="line">
                <c:if test="${edit}">
                    <display:column>
                        <c:if test="${line.noPengenalan ne null}">
                            <s:checkbox name="rmv_hadir"  id="rmv_hadir${line_rowNum-1}" value="${line.idKehadiran}" class="remove"/>
                        </c:if>
                        <c:if test="${line.noPengenalan eq null}">
                            <s:checkbox name="rmv_hadir" disabled="true" id="rmv_hadir${line_rowNum-1}" value="${line.idKehadiran}" class="remove"/>
                        </c:if>
                    </display:column>
                </c:if>
                <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                <display:column title="Nama" class="${line_rowNum}">
                    <c:if test="${line.pihak ne null}">
                        ${line.pihak.pihak.nama}
                    </c:if>
                    <c:if test="${line.pihak eq null}">
                        ${line.nama}
                    </c:if>    
                </display:column>
                <display:column title="Jenis Pihak" class="${line_rowNum}">
                    <c:if test="${line.pihak ne null}">
                        <c:if test="${line.pihak.jenis.kod ne 'WAR' && line.pihak.jenis.kod ne 'TER'}">
                            <font style="text-transform:uppercase;"> ${line.pihak.jenis.nama}</font>
                        </c:if>
                        <c:if test="${line.pihak.jenis.kod eq 'WAR' || line.pihak.jenis.kod eq 'TER'}">
                            <font style="text-transform:uppercase;"> ${line.pihak.kaitan}</font>
                        </c:if>
                    </c:if>
                    <c:if test="${line.pihak eq null}">
                        <font style="text-transform:uppercase;">  ${line.jawatan}</font>
                    </c:if>
                </display:column>
                <c:if test="${edit}">
                    <display:column title="Kehadiran">
                        <s:radio name="listPerbicaraanKehadiran[${line_rowNum - 1}].hadir" value="Y" id="btnY${line_rowNum - 1}"/>&nbsp;Ya
                        <s:radio name="listPerbicaraanKehadiran[${line_rowNum - 1}].hadir" value="N" id="btnN${line_rowNum - 1}"/>&nbsp;Tidak
                    </display:column>
                    <display:column title="Wakil (Jika Ada)">
                        <p align="center"><a href="#" id="wakil${line_rowNum - 1}" onclick="addWakil('${line.idKehadiran}');return false;">Wakil</a></p>
                    </display:column>
                    <display:column title="Catatan"><s:textarea cols="50" name="listPerbicaraanKehadiran[${line_rowNum - 1}].keterangan" id="catatan${line_rowNum - 1}" onkeyup="this.value=this.value.toUpperCase();"/></display:column>
                </c:if>
                <c:if test="${!edit}">
                    <display:column title="Kehadiran">
                        <c:if test="${line.hadir eq 'Y'}">
                            YA
                        </c:if>
                        <c:if test="${line.hadir eq 'N'}">
                            TIDAK
                        </c:if>
                    </display:column>
                    <display:column title="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Wakil&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;">
                        <c:if test="${line.wakilNama ne null}">
                            <p align="center"><a href="#" id="wakil${line_rowNum - 1}" onclick="viewWakil('${line.idKehadiran}');return false;">WAKIL</a></p>
                        </c:if>
                        <c:if test="${line.wakilNama eq null}">
                            TIADA DATA
                        </c:if>
                    </display:column>
                    <display:column title="Catatan"  property="keterangan"/>
                </c:if>
            </display:table>
            <c:if test="${edit}">
                <br>
                <p align="center">
                    <s:button class="btn" value="Tambah Baru" name="new" id="new" onclick="addKehadiran();"/>&nbsp;
                    <s:button class="btn" value="Hapus" name="" onclick="removeKehadiran();"/>&nbsp;
                    <s:button name="simpan" id="save" value="Simpan" class="btn"  onclick="doSubmit(this.form, this.name,'page_div')"/>
                </p>
            </c:if>
            <br>
        </fieldset>
    </div>
</s:form>