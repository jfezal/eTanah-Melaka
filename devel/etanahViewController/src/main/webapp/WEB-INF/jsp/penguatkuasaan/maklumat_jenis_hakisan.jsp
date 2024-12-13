<%--
    Document   : maklumat_jenis_hakisan
    Created on : Oct 31, 2012, 5:16:37 PM
    Author     : latifah.iskak
--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>

<script type="text/javascript">
    
    
    $(document).ready(function() {

        var len = $(".daerah").length;

        for (var i=0; i<=len; i++){
            $('.hakmilik'+i).click( function() {
                window.open("${pageContext.request.contextPath}/hakmilik?hakmilikDetail&idHakmilik="+$(this).text(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
            });
        }
        
        var bil =  ${fn:length(actionBean.senaraiHakmilikPermohonan)};
        for (var i = 0; i < bil; i++){
            if($("#jenisHakisanTanah"+i).val() == "P"){
                document.getElementById("hakisanPenuh"+i).checked = true;
            }else if($("#jenisHakisanTanah"+i).val() == "S"){
                document.getElementById("hakisanSebahagian"+i).checked = true;
            }
        }

    });

    function refreshPageHakisan(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_hakisan_tanah?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
            //refreshPage();
        },'html');
    }

    
    function addJenisHakisan(){
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/4)-(150/4);
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_hakisan_tanah?addRecordJenisHakisan';
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=900,height=500, left=" + left + ",top=" + top);
    }
    
    function editJenisHakisan(id){
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/4)-(150/4);
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_hakisan_tanah?editRecordJenisHakisan&id='+id;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=900,height=500, left=" + left + ",top=" + top);
    }
    
    function removeRecord(id){
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_hakisan_tanah?deleteJenisHakisan&id='+id;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');}
    }
    
    function doAgih(e, f) {
    
        var nota = $('#permohonanNota').val();
        if(nota == 'false'){
            alert('Sila isi maklumat nota/kertas minit terlebih dahulu.');
            return false;
        }
        if(confirm('Adakah anda pasti? Sila semak tab yang lain terlebih dahulu jika belum semak.')) {
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
            var q = $(f).formSerialize();
            f.action = f.action + '?' + e + '&' + q;
            f.submit();
        }
    }
    
    function viewJenisHakisan(id){
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/4)-(150/4);
        var url = '${pageContext.request.contextPath}/penguatkuasaan/laporan_tanah?viewRecordJenisHakisan&idMH='+id;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=900,height=500, left=" + left + ",top=" + top);
    }
    
    
    function validate(){
        return true;
    }



</script>
<s:form beanclass="etanah.view.penguatkuasaan.JenisHakisanActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <s:hidden name="statusNotaExist" id="permohonanNota"/>
            <legend>
                Hakisan Tanah
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiHakmilikPermohonan}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="/penguatkuasaan/maklumat_jenis_hakisan">
                    <display:column title="Bil"><u><a class="popup" onclick="viewJenisHakisan(${line.id})">${line_rowNum}</a></u></display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}" style="vertical-align:baseline"/>
                    <display:column title="No.Lot" property="noLot" class="daerah"></display:column>
                    <display:column title="Kod Lot" property="lot.nama"></display:column>
                    <display:column title="Kategori Tanah" property="jenisPenggunaanTanah.nama"></display:column>
                    <display:column title="Luas" property="luasTerlibat"></display:column>
                    <display:column title="Kod Luas" property="kodUnitLuas.nama"></display:column>
                    <display:column title="Jenis Hakisan">
                        <c:if test="${edit}">
                            <input name="idMohonHakmilik${line_rowNum-1}" value="${line.id}" type="hidden">
                            <input name="jenisHakisanTanah${line_rowNum-1}" id="jenisHakisanTanah${line_rowNum-1}" value="${line.jenisHakisan}" type="hidden">
                            <input type="radio" name="statusHakisan${line_rowNum-1}" id="hakisanPenuh${line_rowNum-1}" value="P" />Penuh
                            <input type="radio" name="statusHakisan${line_rowNum-1}" id="hakisanSebahagian${line_rowNum-1}" value="S"/>Sebahagian
                        </c:if>
                        <c:if test="${view}">
                            <c:if test="${line.jenisHakisan eq 'P'}">
                                Hakisan Penuh
                            </c:if>
                            <c:if test="${line.jenisHakisan eq 'S'}">
                                Hakisan Sebahagian
                            </c:if>
                        </c:if>

                    </display:column>
                    <%-- 
                    <display:column title="Kemaskini">
                        <div align="center">
                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                 id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="editJenisHakisan('${line.id}')"/>
                        </div>
                    </display:column>
                    <display:column title="Hapus">
                        <div align="center">
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeRecord('${line.id}');"/>
                        </div>
                    </display:column>
                    <s:button name="addRecord" id="addRecord" value="Tambah" class="btn" onclick="addJenisHakisan();"/>
                    --%>

                </display:table>

                <c:if test="${edit}">
                    <p align="center">
                        <s:button class="btn" value="Simpan" name="simpanJenisHakisan" id="simpan" onclick="if(validate())doSubmit(this.form, this.name,'page_div')"/>
                        <s:button name="generateIdPerserahan" id="generateIdPerserahan" value="Selesai" class="btn" onclick="doAgih(this.name, this.form);"/>
                    </p>
                </c:if>






            </div>
        </fieldset>
    </div>
</s:form>