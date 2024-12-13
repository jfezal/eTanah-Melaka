<%-- 
    Document   : kutipan_tanpa_denda
    Created on : Mar 30, 2011, 5:09:11 PM
    Author     : abdul.hakim
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<div align="center"><table style="width:99.2%" bgcolor="green">
        <tr>
            <td width="100%" height="20" >
                <div style="background-color: green;" align="center">
                    <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Kutipan Tanpa Denda</font>
                </div>
            </td>
        </tr>
    </table></div>
<script type="text/javascript">
    function checking(){
        var kump = document.getElementById('kumpulan');
        var acc = document.getElementById('acc');
        var hm = document.getElementById('hmilik');
        if((kump.value == "")&&(acc.value == "")&&(hm.value == "")){
            alert('Sila Masukkan Nama Kumpulan atau ID Hakmilik untuk membuat carian.');
            return false;
        }else{return true}
    }
</script>

<script type="text/javascript">
    $(document).ready(function() {
        $('#tagAkaun').hide();
        $('#hm').hide();
        var l = ${fn:length(actionBean.senaraiTagAkaun)};
        if(l > 0){
            $('#tagAkaun').show();
            $('#nxt').show();
            $('#hps').hide();
        }else{$('#hm').show();$('#hps').show();$('#nxt').hide();}

        var mx = ${actionBean.max};
        var mn = ${actionBean.min};
        if(mn > mx){$('#tunggak').hide();}
        var kn = document.getElementById('negeri');
        if(kn.value == 'negeriSembilan'){$('#account').hide();}
    });
    function cetak(f, id2){
        var queryString = $(f).formSerialize()
        window.open("${pageContext.request.contextPath}/hasil/kutipan_hasil?cetak&"+queryString+"&idKew="+id2, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=900,height=300");
    }
</script>

<stripes:form beanclass="etanah.view.stripes.hasil.KutipanTanpaDendaActionBean" id="kutipan_tanpa_denda">
    <s:messages/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Carian Kumpulan</legend>
            <stripes:text name="kodNegeri" id="negeri" style="display:none"/>

            <p id="namaKumplan">
                <label>Nama Kumpulan :</label>
                <stripes:text name="namaKumpulan" id="kumpulan" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>

            <p id="account">
                <label>Nombor Akaun :</label>
                <stripes:text name="akaun.noAkaun" id="acc" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            
            <p id="hakmilik">
                <label>ID Hakmilik :</label>
                <stripes:text name="hakmilik.idHakmilik" id="hmilik" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>

            <p>
                <label>&nbsp;</label>
                <stripes:submit name="Step2" value="Cari" class="btn" onclick="return checking();"/>
                <stripes:button name=" " value="Isi Semula" class="btn" onclick="clearText('kutipan_tanpa_denda');"/>
            </p><br>

        </fieldset>
    </div>
    <p></p>
    <c:if test="${actionBean.flag}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Hasil Carian</legend>
                <%--<p>
                    <label>Nama Kumpulan : </label>
                    ${actionBean.namaKumpulan}
                </p>

                <p>
                    <label>Catatan : </label>
                    ${actionBean.tagAkaun.catatan}
                </p>--%>

                <p>
                <div class="content" align="center" id="tagAkaun">
                    <display:table class="tablecloth" name="${actionBean.senaraiTagAkaun}" id="line">
                        <display:column title="Pilih">
                            <div align="center"><s:radio name="rbtIdKumpulan" value="${line.idTag}"/></div>
                        </display:column>
                        <display:column title="Bil."><div align="center">${line_rowNum}.</div></display:column>
                        <display:column property="idTag" title="ID Kumpulan"/>
                        <display:column property="nama" title="Nama Kumpulan"/>
                        <display:column property="catatan" title="Catatan"/>
                        <display:column property="cawangan.daerah.nama" title="Cawangan"/>
                        <display:column title="Tarikh Masuk">
                            <fmt:formatDate value="${line.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy hh:mm aa"/>
                        </display:column>
                        <%--<display:column style="text-align:right" property="akaun.baki" title="Amaun (RM)" format="{0,number, 0.00}"/>--%>
                    </display:table>
                </div>

                <div class="content" id="hm">
                    <p>
                        <label>ID Hakmilik :</label>
                        ${actionBean.akaun.hakmilik.idHakmilik}
                    </p>
                    <p>
                        <label>Jumlah Perlu Dibayar :</label>
                        <fmt:formatNumber value="${actionBean.akaun.baki}" pattern="RM #,###,##0.00"/>
                    </p>
                    <p id="tunggak">
                        <label>Tahun Tunggakan :</label>
                        ${actionBean.min} - ${actionBean.max}
                    </p>

                    <p align="center">
                    <table align="center" border="0" class="tablecloth">
                        <tr>
                            <th width="170">Perkara</th>
                            <th width="170">Semasa (RM)</th>
                            <th width="170">Tunggakan (RM)</th>
                        </tr>
                        <tr align="center">
                            <th width="170">Cukai Tanah</th>
                            <td class="tdLabel">
                                <center><font face="Tahoma" color="black">
                                        <fmt:formatNumber  value="${actionBean.hakmilik.cukaiSebenar}" pattern="#,###,##0.00"/>
                                    </font></center>
                            </td>
                            <td class="tdLabel">
                                <center><font face="Tahoma" color="black">
                                        <fmt:formatNumber  value="${actionBean.tunggakan}" pattern="#,###,##0.00"/>
                                    </font></center>
                            </td>
                        </tr>
                        <tr align="center">
                            <th width="170">Denda</th>
                            <td class="tdLabel">
                                <center><font face="Tahoma" color="black">
                                        <fmt:formatNumber  value="${actionBean.dendaSemasa}" pattern="#,###,##0.00"/>
                                    </font></center>
                            </td>
                            <td class="tdLabel">
                                <center><font face="Tahoma" color="black">
                                        <fmt:formatNumber  value="${actionBean.dendaTunggakan}" pattern="#,###,##0.00"/>
                                    </font></center>
                            </td>
                        </tr>
                        <tr align="center">
                            <th width="170">Remisyen</th>
                            <td class="tdLabel">
                                <center><font face="Tahoma" color="black">
                                        <fmt:formatNumber  value="${actionBean.remisyen}" pattern="#,###,##0.00"/>
                                    </font></center>
                            </td>
                            <td><center>-</center></td>
                        </tr>
                        <tr align="center">
                            <th width="170">Notis</th>
                            <td><center>-</center></td>
                            <td class="tdLabel">
                                <center><font face="Tahoma" color="black">
                                        <fmt:formatNumber  value="${actionBean.notis}" pattern="#,###,##0.00"/>
                                    </font></center>
                            </td>
                        </tr>
                    </table>
                    </p>

                </div>
                </p>
            </fieldset>
        </div>

        <div align="center"><table style="width:99.2%" bgcolor="green">
                <tr>
                    <td align="right">
                        <stripes:submit name="Step3" value="Seterusnya" class="btn" id="nxt"/>
                        <stripes:submit name="Step5" value="Hapus" class="btn" id="hps"/>
                        <%--<stripes:submit name="kembali" value="Kembali" class="btn"/>--%>
                    </td>
                </tr>
            </table></div>
        </c:if>
    </stripes:form>
