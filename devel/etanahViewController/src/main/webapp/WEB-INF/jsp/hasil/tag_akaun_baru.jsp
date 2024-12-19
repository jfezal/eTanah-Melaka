<%-- 
    Document   : tag_akaun_baru
    Created on : Mar 30, 2011, 3:04:32 PM
    Author     : abu.mansur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript">
    function validateSubmit(f){
        var nama = $("#nama").val();
        if(nama == ''){
            alert("Sila masukkan data di ruangan bertanda ( * ).")
            return false;
        }else{
            return true;
        }
    }

    function kembali(f){
        f.action = '${pageContext.request.contextPath}/hasil/kumpulan_akaunTAG';
        f.submit();
    }
    
    function cetak(id){
        var report ='HSL_ID_KUMP_NS.rdf';
        var url = "reportName="+report+"%26report_p_id_tag="+id;
        window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url="+url, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
    }

    function hapusAkaunAhli(idHakmilik, idAhli){
        if(confirm("Anda pasti untuk hapuskan ID Hakmilik :"+idHakmilik+" ?")){
            <%--var f = document.kumpulanAhli;--%>
            <%--var a = $(f).formSerialize();
            alert(a);--%>
            <%--var url = '${pageContext.request.contextPath}/hasil/kumpulan_akaunTAG_baru?hapusAhli&'+a+'&idAhli='+idAhli;--%>
            <%--alert(url);
            f.action = url;
            f.submit();--%>
        }
    }

    function kehapusanAhli(idHakmilik, idAhli){
        if(confirm("Anda pasti untuk hapuskan ID Hakmilik :"+idHakmilik+" ?")){
            var f = document.kumpulanAhli;
            var url = "${pageContext.request.contextPath}/hasil/kumpulan_akaunTAG_baru?hapusAhli&idAhli="+idAhli;
            f.action = url;
            f.submit();
        }
    }
</script>

<div align="center">
    <table style="width:99.2%" bgcolor="green">
        <tr>
            <td width="100%" height="20" >
                <div style="background-color: green;" align="center">
                    <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Carian Kumpulan</font>
                </div>
            </td>
        </tr>
    </table>
</div>
<s:form name="kumpulanAhli" beanclass="etanah.view.stripes.hasil.KumpulanAkaunTAGBaruActionBean" id="kump_baru">
    <s:errors/>
    <s:messages/>
    <div class="subtitle">
    <fieldset class="aras1">
        <legend>Maklumat Kumpulan</legend>
        <s:hidden name="tagAkaun.idTag"/>
        <c:if test="${actionBean.showPanelAhli}">
            <p>
                <label>ID Kumpulan :</label>
                ${actionBean.tagAkaun.idTag}
            </p>
        </c:if>
        <p>
            <label><em>*</em>Nama Kumpulan :</label>
            <s:text name="tagAkaun.nama" size="30" maxlength="30" id="nama" onkeyup="this.value=this.value.toUpperCase();"/>
        </p>
        <p>
            <label>Catatan :</label>
            <s:textarea id="catat" name="tagAkaun.catatan" cols="40" rows="4" onkeyup="this.value=this.value.toUpperCase();"/>
        </p>        
        <p>
            <label>&nbsp;</label>
            <%--<c:if test="${actionBean.peng.kodCawangan.kod eq actionBean.senaraiKumpulanAhli[0].cawangan.kod or actionBean.tagAkaun eq null}">--%>
            <c:if test="${actionBean.tagAkaun eq null}">
                <s:submit name="simpanKumpulan" value="Simpan" class="btn" onclick="return validateSubmit(this.form);"/>
                <s:button name=" " value="Isi Semula" class="btn" onclick="clearText('kump_baru');"/>
            </c:if>
            <s:submit name="" value="Kembali" class="btn" onclick="kembali(this.form);"/>
        </p>
    </fieldset>
    </div>
    <c:if test="${actionBean.showPanelAhli}">
        <div class="content" align="center">
            <fieldset class="aras1">
                   <legend>Maklumat Hakmilik</legend>
                   <c:if test="${actionBean.peng.kodCawangan.kod eq actionBean.tagAkaun.cawangan.kod}">
                       <p align="center">
                           <s:submit name="updates" value="Tambah Hakmilik" class="longbtn"/> &nbsp;
                           <s:button name="" value="Cetak" class="btn" onclick="cetak('${actionBean.tagAkaun.idTag}');"/> &nbsp;
                        </p>
                   </c:if>
                    <div align="left">
                       <font size="2" color="black">Petunjuk :</font>
                        <p>
                            <img src="${pageContext.request.contextPath}/images/not_ok.gif"/> - <font size="2" color="black">Hapus</font>
                            &nbsp;<b>|</b>&nbsp;
                            <img src="${pageContext.request.contextPath}/images/lock.gif"/> - <font size="2" color="black">Tidak Dibenarkan Hapus</font>
                        </p>
                    </div>
                   <display:table class="tablecloth" name="${actionBean.senaraiKumpulanAhli}" cellpadding="0" 
                                  cellspacing="0" id="line" requestURI="/hasil/kumpulan_akaunTAG_baru" pagesize="10" excludedParams="hapusAhli simpanAhli">
                       <display:column title="Bil" sortable="true"><div align="center">${line_rowNum}</div></display:column>
                       <c:if test="${actionBean.negeri eq 'melaka'}">
                           <display:column property="akaun.noAkaun" title="No. Akaun"/>
                       </c:if>
                       <display:column property="akaun.hakmilik.idHakmilik" title="ID Hakmilik"/>
                       <display:column title="Nama Pemilik Tanah (No Pengenalan)" style="width:25%;">
                            <ol>                               
                                <c:forEach items="${line.akaun.hakmilik.senaraiPihakBerkepentingan}" var="senarai" varStatus="a">
                                    <c:if test="${(senarai.jenis.kod eq 'PM') and senarai.aktif eq 'Y'}">
                                        <li>
                                            <c:out value="${senarai.pihak.nama}" />  
                                            <c:if test="${senarai.pihak.noPengenalan ne null}">
                                                (<c:out value="${senarai.pihak.noPengenalan}" />)
                                            </c:if>
                                        </li>
                                    </c:if>
                                </c:forEach>
                            </ol>
                        </display:column>
                       <display:column property="cawangan.name" title="Daerah Daftar"/>
                       <display:column property="infoAudit.tarikhMasuk" title="Tarikh Daftar" format="{0,date,dd/MM/yyyy hh:mm:ss a}"/>
                       <display:column title="Tindakan">
                           <div align="center">
                            <c:choose>
                                <c:when test="${actionBean.peng.kodCawangan.kod eq line.cawangan.kod}">
                                    <img alt='Klik Untuk Hapus' src='${pageContext.request.contextPath}/images/not_ok.gif'
                                     onclick="kehapusanAhli('${line.akaun.hakmilik.idHakmilik}','${line.idAhli}');"
                                     onmouseover="this.style.cursor='pointer';" title="Hapus Hakmilik :${line.akaun.hakmilik.idHakmilik}"/>
                                </c:when>
                                <c:otherwise>
                                    <div align="center">
                                        <img alt='Tidak Dibenarkan Hapus' src='${pageContext.request.contextPath}/images/lock.gif' />
                                    </div>
                                </c:otherwise>
                            </c:choose>
                           </div>
                        </display:column>
                   </display:table>
            </fieldset>
        </div>
    </c:if>
</s:form>
