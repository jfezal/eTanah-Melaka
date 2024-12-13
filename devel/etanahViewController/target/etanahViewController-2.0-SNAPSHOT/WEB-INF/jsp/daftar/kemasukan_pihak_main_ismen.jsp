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

    function doEdit(d, j, d1){
        window.open("${pageContext.request.contextPath}/daftar/pihak_kepentingan?showEditPemohonPenerimaForm&jenis_pihak="
            +j+"&id="+d + "&idPihak=" +d1, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
    }

    function add(action){
        var len = $('.nama').length;
        var param = '';
        var param2 = '';
        doBlockUI();

        for(var i=1; i<=len; i++){

            var ckd = $('#chkbox'+i).is(":checked");
            if(ckd){
                param = param + '&ids=' + $('#chkbox'+i).val();
                param2 = param2 + '&idHakmiliks=' +$('#idHakmilik' + i).val();
            }
        }
        
        if(param == ''){
            alert('Tiada pemohon.');
            doUnBlockUI();
            return;
        }
        var url = '${pageContext.request.contextPath}/daftar/pihak_kepentingan?' +action+param+param2;

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

    function rm(action, cn, jenis, id){
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
        var url = '${pageContext.request.contextPath}/daftar/pihak_kepentingan?' +action+param + '&jenisPihak=' + jenis;

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

</script>
<div class="subtitle displaytag">
    <s:messages/>
    <s:errors/>
    <s:form beanclass="etanah.view.daftar.PihakKepentinganAction" name="form1">        
        <fieldset class="aras1">
            <legend>Senarai Pemilik</legend>

            <p align="center">
                <display:table style="width:70%;" class="tablecloth" name="${actionBean.senaraiKeempunyaan}"
                               cellpadding="0" cellspacing="0" id="line">
                    <c:if test="${edit}">
                        <display:column>
                            <s:checkbox name="checkbox" id="chkbox${line_rowNum}" value="${line.pihak.idPihak}"/>
                            <s:hidden name="id_hakmilik" id="idHakmilik${line_rowNum}" value="${line.hakmilik.idHakmilik}"/>
                        </display:column>
                    </c:if>
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column  title="Nama" property="pihak.nama" class="nama"/>
                    <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                    <display:column title="Bahagian yang dimiliki" ><div align="center">
                        ${line.syerPembilang}/${line.syerPenyebut}</div>
                    </display:column>
                    <display:column property="jenis.nama" title="Jenis Pihak"/>
                </display:table>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:button name="savePihakIsmen" onclick="add(this.name);" value="Simpan" class="btn"/>
            </p>
            <br/>
        </fieldset>
        <br/>
        <fieldset class="aras1">
            <legend>Senarai Pemilik Menanggung Terlibat</legend>
            <div class="content" align="center">
                <display:table style="width:70%;" class="tablecloth" name="${actionBean.senaraiPemohon}"
                               cellpadding="0" cellspacing="0" id="line1">
                    <c:if test="${edit}">
                        <display:column>
                            <s:checkbox name="checkbox" id="rm_${line1_rowNum}" value="${line1.idPemohon}"/>
                        </display:column>
                    </c:if>
                    <display:column title="Bil">${line1_rowNum}</display:column>
                    <display:column title="Nama Pihak" property="pihak.nama" class="remove"/>
                    <display:column title="Alamat">${line1.pihak.suratAlamat1}
                        <c:if test="${line1.pihak.suratAlamat1 ne null && line1.pihak.suratAlamat2 ne null}"> , </c:if>
                        ${line1.pihak.suratAlamat2}
                        <c:if test="${line1.pihak.suratAlamat3 ne null}"> , </c:if>
                        ${line1.pihak.suratAlamat3}
                        <c:if test="${line1.pihak.suratAlamat4 ne null}"> , </c:if>
                        ${line1.pihak.suratAlamat4}
                        <c:if test="${line1.pihak.suratPoskod ne null}">,</c:if>
                        ${line1.pihak.suratPoskod}
                        <c:if test="${line1.pihak.suratNegeri.kod ne null}">,</c:if>
                        ${line1.pihak.suratNegeri.nama}
                    </display:column>
                    <c:if test="${edit}">
                        <display:column title="Kemaskini">
                            <p align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                     onclick="doEdit('${line1.idPemohon}', 'pemohon','${line1.pihak.idPihak}');return false;" onmouseover="this.style.cursor='pointer';">
                            </p>
                        </display:column>
                    </c:if>
                </display:table>
            </div>
            <c:if test="${fn:length(actionBean.senaraiPemohon) > 0}">
                <p>
                    <label>&nbsp;</label>
                    <s:button name="delete" onclick="rm(this.name, 'remove', 'pemohon', 'rm');" value="Hapus" class="btn"/>
                </p>
            </c:if>
        </fieldset>
        <br/>
        <fieldset class="aras1">
            <legend>Senarai Pemilik Menguasai Terlibat</legend>
            <div class="content" align="center">
                <display:table style="width:70%;" class="tablecloth" name="${actionBean.senaraiPermohonanPihak}"
                               cellpadding="0" cellspacing="0" id="line2">
                    <c:if test="${edit}">
                        <display:column>
                            <s:checkbox name="checkbox" id="rmx_${line2_rowNum}" value="${line2.idPermohonanPihak}"/>
                        </display:column>
                    </c:if>
                    <display:column title="Bil">${line2_rowNum}</display:column>
                    <display:column title="Nama Pihak" property="pihak.nama" class="remove1"/>
                    <display:column title="Alamat">${line2.pihak.suratAlamat1}
                        <c:if test="${line2.pihak.suratAlamat2 ne null}"> , </c:if>
                        ${line2.pihak.suratAlamat2}
                        <c:if test="${line2.pihak.suratAlamat3 ne null}"> , </c:if>
                        ${line2.pihak.suratAlamat3}
                        <c:if test="${line2.pihak.suratAlamat4 ne null}"> , </c:if>
                        ${line2.pihak.suratAlamat4}
                        <c:if test="${line2.pihak.suratPoskod ne null}">,</c:if>
                        ${line2.pihak.suratPoskod}
                        <c:if test="${line2.pihak.suratNegeri.kod ne null}">,</c:if>
                        ${line2.pihak.suratNegeri.nama}
                    </display:column>
                    <c:if test="${edit}">
                        <display:column title="Kemaskini">
                            <p align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                     onclick="doEdit('${line2.idPermohonanPihak}', 'penerima','${line2.pihak.idPihak}');return false;" onmouseover="this.style.cursor='pointer';">
                            </p>
                        </display:column>
                    </c:if>                                          
                </display:table>
            </div>
            <c:if test="${fn:length(actionBean.senaraiPermohonanPihak) > 0}">
                <p>
                    <label>&nbsp;</label>
                    <s:button name="delete" onclick="rm(this.name, 'remove1', 'penerima', 'rmx');" value="Hapus" class="btn"/>
                </p>
            </c:if>
        </fieldset>
    </s:form>
</div>
