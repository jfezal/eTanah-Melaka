<%--
    Document   : kemasukan_pihak_main
    Created on : Nov 5, 2010, 11:54:31 AM
    Author     : fikri
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

    var DELIM = "__^$__";

    function doEdit(d, j, d1){
        var idHakmilik = $('#hakmilik').val();
        window.open("${pageContext.request.contextPath}/daftar/pihak_kepentingan?showEditPemohonPenerimaForm&jenis_pihak="
            +j+"&id="+d + "&idPihak=" +d1 + '&idHakmilik=' + idHakmilik, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes");
    }

    function addNew(action){
        var idHakmilik = $('#hakmilik').val();
        var len = $('.nama').length;
        var param = '';
        doBlockUI();

        for(var i=1; i<=len; i++){

            var ckd = $('#chkbox'+i).is(":checked");
            if(ckd){
                param = param + '&uids=' + $('#chkbox'+i).val();
            }
        }

        if(param == ''){
            alert('Tiada pemohon.');
            doUnBlockUI();
            return;
        }

        var url = '${pageContext.request.contextPath}/daftar/pihak_kepentingan?' +action+param+'&jenisPihak=pemohon&idHakmilik=' + idHakmilik;

        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function (xhr, ajaxOptions, thrownError){
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success : function(data){
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });
    }


    function remove2(action, cn, jenis, id){       
        var idHakmilik = $('#hakmilik').val();
        var len = $('.' + cn).length;
        var param = '';
        doBlockUI();        

        for(var i=1; i<=len; i++){

            var ckd = $('#' + id +'_'+i).is(":checked");
            if(ckd){
                param = param + '&uids=' + $('#' + id +'_'+i).val();
            }
        }        

        if(param == ''){
            alert('Tiada pemohon.');
            doUnBlockUI();
            return;
        }
        var url = '${pageContext.request.contextPath}/daftar/pihak_kepentingan?' +action+param + '&jenisPihak=' + jenis + '&idHakmilik=' + idHakmilik;

        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function (xhr, ajaxOptions, thrownError){
                alert("error=" + xhr.responseText);
                doUnBlockUI();
                // doPopupMsg('icons/alertIcon.png',"Kemaskini gagal!");
            },
            success : function(data){
                $('#page_div').html(data);
                //doPopupMsg('icons/KnobValidGreen.png','Kemaskini Data Berjaya');
                doUnBlockUI();
            }
        });
    }

    function reload (val) {
        doBlockUI();
        var url = '${pageContext.request.contextPath}/daftar/pihak_kepentingan?reload&idHakmilik=' + val;
        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function (xhr, ajaxOptions, thrownError){
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success : function(data){
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });

    }

    function doOpen() {
        var idHakmilik = $('#hakmilik').val();
        var url = '${pageContext.request.contextPath}/daftar/pihak_kepentingan?showSearchForm&idHakmilik=' + idHakmilik;
        window.open(url,  "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes");
    }



     function doPopupTuanTanah() {
         window.open("${pageContext.request.contextPath}/common/pemohon?showFormPopup&idHakmilik=" + $('#hakmilik').val(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=910,height=700,scrollbars=yes");
        }

</script>


<div class="subtitle displaytag">
    <s:messages/>
    <s:errors/>
    <s:form beanclass="etanah.view.daftar.PihakKepentinganAction" name="form1">
        <fieldset class="aras1">
            <legend>Senarai Hakmilik Terlibat</legend>
            <p>
                <label>Hakmilik :</label>
                <s:select name="idHakmilik" onchange="reload(this.value);" id="hakmilik">
                    <c:forEach items="${actionBean.senaraiHakmilikTerlibat}" var="item" varStatus="line">
                        <s:option value="${item.hakmilik.idHakmilik}" style="width:400px">
                            ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                        </s:option>
                    </c:forEach>
                </s:select>
            </p>
            <br/>
        </fieldset>        
        <br/>
        <fieldset class="aras1">
            <c:if test="${fn:startsWith(actionBean.permohonan.kodUrusan.kod,'KV')}">
                <legend>Senarai Kaveator</legend>
            </c:if>
            <c:if test="${!fn:startsWith(actionBean.permohonan.kodUrusan.kod,'KV')}">
                <legend>Senarai Pihak Baru Terlibat</legend>
            </c:if>
            <div class="content" align="center">
                <display:table style="width:80%;" class="tablecloth" name="${actionBean.senaraiPermohonanPihak}"
                               cellpadding="0" cellspacing="0" id="line2">
                    <c:if test="${edit}">
                        <display:column>
                            <s:checkbox name="checkbox" id="rm_mp_${line2_rowNum}" value="${line2.idPermohonanPihak}"/>
                        </display:column>
                    </c:if>
                    <display:column title="Bil">${line2_rowNum}</display:column>
                    <c:choose>
                        <c:when test="${empty line2.nama}">
                            <display:column title="Nama Pihak" property="pihak.nama" class="remove"/>
                        </c:when>
                        <c:otherwise>
                            <display:column title="Nama Pihak" property="nama" class="remove"/>
                        </c:otherwise>
                    </c:choose>
                    
                    <display:column title="Alamat Surat Menyurat">
                        <c:if test="${!empty line2.alamatSurat}">
                            ${line2.alamatSurat.alamatSurat1}
                        <c:if test="${line2.alamatSurat.alamatSurat1 ne null && line2.alamatSurat.alamatSurat2 ne null}"> , </c:if>
                        ${line2.alamatSurat.alamatSurat2}
                        <c:if test="${line2.alamatSurat.alamatSurat2 ne null && line2.alamatSurat.alamatSurat3 ne null}"> , </c:if>
                        ${line2.alamatSurat.alamatSurat3}
                        <c:if test="${line2.alamatSurat.alamatSurat3 ne null && line2.alamatSurat.alamatSurat4 ne null}"> , </c:if>
                        ${line2.alamatSurat.alamatSurat4}
                        <c:if test="${line2.alamatSurat.alamatSurat4 ne null && line2.alamatSurat.poskodSurat ne null}">,</c:if>
                        ${line2.alamatSurat.poskodSurat}
                        <c:if test="${line2.alamatSurat.poskodSurat ne null && line2.alamatSurat.negeriSurat.kod ne null}">,</c:if>
                        ${line2.alamatSurat.negeriSurat.nama}
                        </c:if>
                        <c:if test="${empty line2.alamat}">
                            ${line2.pihak.suratAlamat1}
                        <c:if test="${line2.pihak.suratAlamat1 ne null && line2.pihak.suratAlamat2 ne null}"> , </c:if>
                        ${line2.pihak.suratAlamat2}
                        <c:if test="${line2.pihak.suratAlamat2 ne null && line2.pihak.suratAlamat3 ne null}"> , </c:if>
                        ${line2.pihak.suratAlamat3}
                        <c:if test="${line2.pihak.suratAlamat3 ne null && line2.pihak.suratAlamat4 ne null}"> , </c:if>
                        ${line2.pihak.suratAlamat4}
                        <c:if test="${line2.pihak.suratAlamat4 ne null && line2.pihak.suratPoskod ne null}">,</c:if>
                        ${line2.pihak.suratPoskod}
                        <c:if test="${line2.pihak.suratPoskod ne null && line2.pihak.suratNegeri.kod ne null}">,</c:if>
                        ${line2.pihak.suratNegeri.nama}
                            
                        </c:if>
                        
                    </display:column>                  
                                  
                    <c:if test="${edit}">
                        <display:column title="Kemaskini">
                            <p align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                     onclick="doEdit('${line2.idPermohonanPihak}', 'penerima','${line2.pihak.idPihak}');return false;"
                                     onmouseover="this.style.cursor='pointer';">
                            </p>
                        </display:column>
                    </c:if>
                </display:table>
            </div>

            <c:if test="${edit}">

                <p align="center">
                    <label></label>
                    <c:if test="${fn:length(actionBean.senaraiPermohonanPihak) > 0}">                       
                        <s:button name="delete" onclick="remove2(this.name, 'remove', 'penerima', 'rm_mp');" value="Hapus" class="btn"/>&nbsp;
                    </c:if>
                    <s:button class="btn" value="Tuan Tanah" name="semak" id="semak" onclick="doPopupTuanTanah();"/>&nbsp;
                    <s:button name="add" onclick="doOpen();" value="Tambah" class="btn"/>
                </p>

            </c:if>

        </fieldset>
    </s:form>
</div>
