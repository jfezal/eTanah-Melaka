<%-- 
    Document   : notis_gantian
    Created on : Oct 8, 2010, 9:37:21 PM
    Author     : mdizzat.mashrom
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript">


    function edit(id){
        window.open("${pageContext.request.contextPath}/hasil/rekod_penghantaran?popup&idHakmilik="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=300");
    }

    function save(event, f){
        alert('hohoho');
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);

            setTimeout(function(){
                self.close();
            }, 100);
        },'html');
    }

    function validate(){

        var bil = parseInt(${actionBean.bil});

        for (var i = 0; i < bil; i++){

            var nama = document.getElementById('namela'+i);
            var kod = document.getElementById('kodPenyampaian'+i);
            var kod2 = document.getElementById('kodPenghantaran'+i);
            var tarikh = document.getElementById('tarikhDihantar'+i);
            var tarikh2 = document.getElementById('tarikhTerima'+i);
            var catat = document.getElementById('catatanTerima'+i);
            if(nama.value == "" ){
                alert("Sila Masukkan Nama Penghantar Notis");
                $('#namela'+i).focus();
                return false;
            }
            if(kod.value == "" ){
                alert("Sila Pilih Status Penyampaian");
                $('#kodPenyampaian'+i).focus();
                return false;
            }
            if(kod2.value == "" ){
                alert("Sila Pilih Cara Penghantaran");
                $('#kodPenghantaran'+i).focus();
                return false;
            }
            if(tarikh.value == "" ){
                alert("Sila Pilih Tarikh Hantar");
                $('#tarikhDihantar'+i).focus();
                return false;
            }
            if(tarikh2.value == "" ){
                alert("Sila Pilih Tarikh Terima");
                $('#tarikhTerima'+i).focus();
                return false;
            }
            if(catat.value == "" ){
                alert("Sila Masukkan Catatan");
                $('#catatanTerima'+i).focus();
                return false;
            }

        }

        return true;
    }

    function muatNaikForm(notis) {
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/lelong/notis_gantian?popupUpload&idNotis='+notis;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }

    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

</script>

<s:form beanclass="etanah.view.stripes.lelong.NotisGantianActionBean">

    <s:messages/>
    <s:errors/>&nbsp;

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Penghantaran Notis Gantian
            </legend><br>

            <p class=instr>
                *<em>Petunjuk :</em>
            </p>
            <p class=instr>
                <em>H:</em> Tarikh Hantar
                <em>T:</em> Tarikh Terima
            </p>
            <font size="2" color="black"></font>
            <p>
                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                     width="20" height="20" /> - <font size="1" color="black">Papar Dokumen</font>&nbsp;&nbsp;&nbsp;
                <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                     width="20" height="20" /> - <font size="1" color="black">Muat Naik Dokumen</font>&nbsp;&nbsp;&nbsp;
                <%--<img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png"
                             width="20" height="20" /> - <font size="1" color="black">Imbas Dokumen</font>--%>
            </p><br>

            <div class="content" align="center">

                <display:table class="tablecloth" name="${actionBean.listNotis}"
                               cellpadding="0" cellspacing="0" requestURI="/lelong/notis_gantian" id="line">
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column title="Nama">
                        ${line.pihak.pihak.nama}<br>
                        (
                        <c:if test="${line.pihak.jenis.kod ne 'PG'}">
                            Penggadai
                        </c:if>
                        <c:if test="${line.pihak.jenis.kod eq 'PG'}">
                            ${line.pihak.jenis.nama}
                        </c:if>)
                    </display:column>
                    <display:column title="Nama Penghantar Notis" >
                        <s:text name="namaPenghantar${line_rowNum-1}" id="namela${line_rowNum - 1}" >${line.penghantarNotis}</s:text>
                    </display:column>

                    <display:column title="Status Penyampaian" class="${line_rowNum}">

                        <c:if test="${line.kodStatusTerima.nama ne null}" >
                            <s:select name="kodPenyampaian${line_rowNum-1}" id="kodPenyampaian${line_rowNum-1}" style="width:139px;">
                                <s:option >${line.kodStatusTerima.nama}</s:option>
                                <s:option value="TM">Diterima</s:option>
                                <s:option value="XT">Tidak Diterima</s:option>
                            </s:select>
                        </c:if>

                        <c:if test="${line.kodStatusTerima.nama eq null}" >
                            <s:select name="kodPenyampaian${line_rowNum-1}" id="kodPenyampaian${line_rowNum-1}" style="width:139px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:option value="TM">Diterima</s:option>
                                <s:option value="XT">Tidak Diterima</s:option>
                            </s:select>
                        </c:if>

                    </display:column>

                    <display:column title="Cara Penghantaran" class="${line_rowNum}">

                        <c:if test="${line.kodCaraPenghantaran.nama ne null}">
                            <s:select name="kodPenghantaran${line_rowNum-1}" id="kodPenghantaran${line_rowNum-1}" style="width:139px;">
                                <s:option>${line.kodCaraPenghantaran.nama}</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodCaraPenghantaran}" label="nama" value="kod"/>
                            </s:select>

                        </c:if>

                        <c:if test="${line.kodCaraPenghantaran.nama eq null}">
                            <s:select name="kodPenghantaran${line_rowNum-1}" id="kodPenghantaran${line_rowNum-1}" style="width:139px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodCaraPenghantaran}" label="nama" value="kod"/>
                            </s:select>
                        </c:if>

                    </display:column>


                    <display:column title="Tarikh" class="${line_rowNum}">
                        <p>H : <s:text class="datepicker" id="tarikhDihantar${line_rowNum-1}" name="tarikhDihantar${line_rowNum-1}" formatPattern="dd/MM/yyyy" style="width:100px;" value="${line.tarikhHantar}"/>
                            &nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font></p>
                        <p>T : <s:text class="datepicker" id="tarikhTerima${line_rowNum-1}" name="tarikhTerima${line_rowNum-1}" formatPattern="dd/MM/yyyy" style="width:100px;" value="${line.tarikhTerima}"/>
                            &nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font></p>
                        </display:column>
                        <display:column  title="Catatan" class="${line_rowNum}" >
                            <s:textarea name="catatanTerima${line_rowNum-1}" id="catatanTerima${line_rowNum-1}" value="${actionBean.notis.catatanPenerimaan}" rows="2" cols="15" onblur="this.value=this.value.toUpperCase();" />
                        </display:column>
                        <display:column title="Tindakan">
                        <p align="center">
                            <c:if test="${line.buktiPenerimaan == null}">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                     onmouseover="this.style.cursor='pointer';" onclick="muatNaikForm('${line.idNotis}');return false;" title="Muat Naik Dokumen"/><%-- /
                                <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                     onmouseover="this.style.cursor='pointer';" onclick="scan('${line.idNotis}');return false;" title="Imbas Dokumen"/>--%>
                            </c:if>
                            <c:if test="${line.buktiPenerimaan != null}">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar"
                                     onclick="doViewReport('${line.buktiPenerimaan.idDokumen}');" onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                            </c:if>
                        </p>
                    </display:column>

                </display:table>

            </div>

            <c:if test="${actionBean.button ne true}">
                <p align="right">
                    <s:button class="btn"  name="simpan" value="Simpan" onclick="if(validate())doSubmit(this.form, this.name,'page_div')"/>
                </p>
            </c:if>

            <br>
        </fieldset>
    </div>
</s:form>

