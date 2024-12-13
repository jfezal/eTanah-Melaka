<%--
    Document   : kemasukan_senarai_waris
    Created on : Mar 17, 2010, 12:51:37 PM
    Author     : muhammad.amin
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
    $(document).ready( function(){
        $('.empty').remove();

        var len2 = $(".alamat").length;
         
        for ( var i=0; i<len2; i++){
            var d = $('.x'+i).val();
    
            $('.nama'+i).bind('click',d, function(){
                window.open("${pageContext.request.contextPath}/pihak_berkepentingan?showEditPemohon&idPihak="+d, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
            });
        }
        
    });

    function addNewWaris(){
        window.open("${pageContext.request.contextPath}/consent/kemasukan_waris?warisPopup", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }
    function editWaris(i){
        var d = $('.x'+i).val();
        window.open("${pageContext.request.contextPath}/consent/kemasukan_waris?showEditWaris&idPihak="+d, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }

    function viewPihak(id){
        window.open("${pageContext.request.contextPath}/consent/kemasukan_waris?viewPihakDetail&idPihak="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }

    function removeMultipleWaris() {
        var param = '';



        if(confirm('Adakah anda pasti?')) {

            $('.remove').each(function(index){
                var a = $('#rmv_mp'+index).is(":checked");
                if(a) {
                    param = param + '&idPermohonanPihak=' + $('#rmv_mp'+index).val();
                }
            });
            if(param == ''){
                alert('Sila Pilih Waris terlebih dahulu.');
                return;
            }

            var url = '${pageContext.request.contextPath}/common/mohon_pihak?deleteMultipleWaris'+param;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            }, 'html');
        }
        
    }

</script>
<div class="subtitle displaytag">
    <s:messages/>
    <s:errors/>
    <s:form beanclass="etanah.view.stripes.consent.KemasukanWarisActionBean">

        <fieldset class="aras1">
            <legend>
                Senarai Waris
            </legend>

            <div class="content" align="center">
                <display:table style="width:90%;" class="tablecloth" name="${actionBean.warisList}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/consent/kemasukan_waris">

                    <c:if test="${edit}">
                        <display:column>
                            <s:checkbox name="rmv_mp" id="rmv_mp${line_rowNum-1}" value="${line.idPermohonanPihak}" class="remove"/>
                        </display:column>
                    </c:if>
                    <display:column title="Bil" sortable="true">${line_rowNum}
                        <s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/>
                    </display:column>
                    <display:column  title="Nama" >
                        <a href="#" onclick="viewPihak('${line.pihak.idPihak}');return false;">${line.pihak.nama}</a>
                    </display:column>
                    <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                    <display:column property="kaitan" title="Jenis Waris"/>
                    <display:column title="Alamat Surat-menyurat">${line.pihak.suratAlamat1}
                        <%--<c:if test="${line.pihak.suratAlamat2 ne null}">,</c:if>--%>
                        ${line.pihak.suratAlamat2}
                        <%-- <c:if test="${line.pihak.suratAlamat3 ne null}"> </c:if>--%>
                        ${line.pihak.suratAlamat3}
                        <%-- <c:if test="${line.pihak.suratAlamat4 ne null}">,</c:if>--%>
                        ${line.pihak.suratAlamat4}
                        <%--<c:if test="${line.pihak.suratPoskod ne null}">,</c:if>--%>
                        ${line.pihak.suratPoskod}
                        <%--<c:if test="${line.pihak.suratNegeri.kod ne null}">,</c:if>--%>
                        <font style="text-transform:uppercase;">${line.pihak.suratNegeri.nama}</font>
                    </display:column>

                    <c:if test="${edit}">
                        <display:column title="Kemaskini">
                            <p align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                     onclick="editWaris('${line_rowNum -1}');return false;" onmouseover="this.style.cursor='pointer';">
                            </p>
                        </display:column>

                    </c:if>
                </display:table>
            </div>
            <c:if test="${edit}">
                <p align="center">
                    <s:button class="btn" value="Tambah Baru" name="new" id="new" onclick="addNewWaris();"/>&nbsp;
                    <c:if test="${fn:length(actionBean.warisList) > 0}">
                        <s:button class="btn" value="Hapus" name="" onclick="removeMultipleWaris();"/>&nbsp;
                    </c:if>
                </p>
            </c:if>
        </fieldset>
        <br/>
    </s:form>

</div>
