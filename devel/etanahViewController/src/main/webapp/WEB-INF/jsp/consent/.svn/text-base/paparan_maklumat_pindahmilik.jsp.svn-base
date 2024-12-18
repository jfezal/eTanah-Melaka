<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

    function reload(val) {
        doBlockUI();
        var url = '${pageContext.request.contextPath}/consent/maklumat_pindahmilik?reload&idHakmilik=' + val;
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'html',
            error: function(xhr, ajaxOptions, thrownError) {
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success: function(data) {
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });

    }

</script>

<s:form beanclass="etanah.view.stripes.consent.MaklumatPindahMilikActionBean">

    <fieldset class="aras1">
        <legend>Senarai Hakmilik Terlibat</legend>
        <div align="center">
            <c:if test="${fn:length(actionBean.senaraiHakmilikTerlibat) > 1}">
                <p>
                    <font size="2" color="red">
                        <b>Permohonan Melibatkan Banyak Hakmilik</b>
                    </font>
                </p>
            </c:if>
        </div>
        <div align="center">          
            <font color="#003194" style="font-weight: bold; font-family:Tahoma; font-size: 13px;">
                Hakmilik :
            </font>
            <s:select name="idHakmilik" onchange="reload(this.value);" id="hakmilik">
                <c:forEach items="${actionBean.senaraiHakmilikTerlibat}" var="item" varStatus="line">
                    <s:option value="${item.hakmilik.idHakmilik}" style="width:400px">
                        ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                    </s:option>
                </c:forEach>
            </s:select>
        </div>
        <br/>
    </fieldset>
    <br/>
    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PCMMK' and actionBean.permohonan.kodUrusan.kod ne 'PCPTD' and actionBean.permohonan.kodUrusan.kod ne 'PGDMB'}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Tambahan</legend>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'MCKMM' || actionBean.permohonan.kodUrusan.kod eq 'MCPTG' 
                              || actionBean.permohonan.kodUrusan.kod eq 'PJKMM' || actionBean.permohonan.kodUrusan.kod eq 'SWKMM' 
                              || actionBean.permohonan.kodUrusan.kod eq 'PPTGM' || actionBean.permohonan.kodUrusan.kod eq 'PMKMM' 
                              || actionBean.permohonan.kodUrusan.kod eq 'PJPTD' || actionBean.permohonan.kodUrusan.kod eq 'PMPTD' 
                              || actionBean.permohonan.kodUrusan.kod eq 'MCPTD' || actionBean.permohonan.kodUrusan.kod eq 'SWPTD'}">
                    <p>
                        <label>Jenis Sekatan :</label>
                    <c:if test="${actionBean.hakmilikPermohonan.pegangan ne null}"> 
                        <c:choose>
                            <c:when test="${actionBean.hakmilikPermohonan.pegangan == 'AM'}">KOD A-MELAYU</c:when>
                            <c:when test="${actionBean.hakmilikPermohonan.pegangan == 'AB'}">KOD A-BUMIPUTERA</c:when>
                            <c:when test="${actionBean.hakmilikPermohonan.pegangan == 'B'}">KOD B</c:when>
                            <c:when test="${actionBean.hakmilikPermohonan.pegangan == 'LL'}">LAIN-LAIN</c:when>
                        </c:choose>
                    </c:if>
                    <c:if test="${actionBean.hakmilikPermohonan.pegangan eq null}"> TIADA DATA </c:if>    
                    </p>
                    <p>
                        <label>Jenis Kategori Tanah : :</label>
                    <c:if test="${actionBean.hakmilikPermohonan.penjenisan ne null}"> 
                        <c:choose>
                            <c:when test="${actionBean.hakmilikPermohonan.penjenisan == '1'}">PERTANIAN &nbsp;</c:when>
                            <c:when test="${actionBean.hakmilikPermohonan.penjenisan == '2'}">KEDIAMAN &nbsp;</c:when>
                            <c:when test="${actionBean.hakmilikPermohonan.penjenisan == '3'}">PERUSAHAAN &nbsp;</c:when>
                            <c:when test="${actionBean.hakmilikPermohonan.penjenisan == '4'}">PERNIAGAAN &nbsp;</c:when>
                        </c:choose>
                    </c:if>
                    <c:if test="${actionBean.hakmilikPermohonan.pegangan eq null}"> TIADA DATA </c:if>    
                    </p>
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PMKMM' || actionBean.permohonan.kodUrusan.kod eq 'PPTGM' 
                               || actionBean.permohonan.kodUrusan.kod eq 'PMPTD'}">
                    <p> 
                        <label>Jenis Pindahmilik :</label>
                    <c:if test="${actionBean.permohonanUrusan.pindahmilik ne null}"> ${actionBean.permohonanUrusan.pindahmilik.nama}&nbsp; </c:if>
                    <c:if test="${actionBean.permohonanUrusan.pindahmilik eq null}"> TIADA DATA </c:if> 
                    </p>            
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PJKMM' || actionBean.permohonan.kodUrusan.kod eq 'PJPTD'}">
                    <p> 
                        <label>Keluasan :</label>
                    <c:if test="${actionBean.hakmilikPermohonan.luasBaru ne null && actionBean.hakmilikPermohonan.kodUnitLuasBaru ne null}">
                        <fmt:formatNumber pattern="###0.0000" value="${actionBean.hakmilikPermohonan.luasBaru}"/> ${actionBean.hakmilikPermohonan.kodUnitLuasBaru.nama}</c:if>
                    <c:if test="${actionBean.hakmilikPermohonan.luasBaru eq null && actionBean.hakmilikPermohonan.kodUnitLuasBaru ne null}"> TIADA DATA </c:if> 
                    </p>
                </c:if>
                    <p>
                        <label>Nilai Transaksi (RM) :</label>
                    <c:if test="${actionBean.permohonanUrusan.perjanjianAmaun ne null}"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.permohonanUrusan.perjanjianAmaun}"/></c:if>
                    <c:if test="${actionBean.permohonanUrusan.perjanjianAmaun eq null}"> TIADA DATA </c:if>
                    </p>
                    <p>
                        <label>Tempoh Bayaran :</label>
                    <c:if test="${actionBean.tempohTahun ne null && actionBean.tempohTahun ne '0'}">${actionBean.tempohTahun}&nbsp;TAHUN&nbsp;</c:if>
                    <c:if test="${actionBean.tempohBulan ne null && actionBean.tempohBulan ne '0'}">${actionBean.tempohBulan}&nbsp;BULAN</c:if>
                    <c:if test="${(actionBean.tempohTahun eq null && actionBean.tempohBulan eq null) || (actionBean.tempohTahun eq '0' && actionBean.tempohBulan eq '0') || (actionBean.tempohTahun eq null && actionBean.tempohBulan eq '0')  || (actionBean.tempohTahun eq '0' && actionBean.tempohBulan eq null)}"> TIADA DATA </c:if>
                    </p>
                    <p>
                        <label>Tujuan :</label>
                    <c:if test="${actionBean.permohonanUrusan.sebab ne null}"> ${actionBean.permohonanUrusan.sebab}&nbsp; </c:if>
                    <c:if test="${actionBean.permohonanUrusan.sebab eq null}"> TIADA DATA </c:if>
                    </p>
                    <p>
                        <label>Keterangan :</label>
                    <c:if test="${actionBean.permohonanUrusan.catatan ne null}"> ${actionBean.permohonanUrusan.catatan}&nbsp; </c:if>
                    <c:if test="${actionBean.permohonanUrusan.catatan eq null}"> TIADA DATA </c:if>
                    </p>
                    <br/>
                </fieldset>
            </div>
    </c:if>
    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PCMMK' or actionBean.permohonan.kodUrusan.kod eq 'PCPTD' or actionBean.permohonan.kodUrusan.kod eq 'PGDMB'}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Pindah Milik</legend>
                <p>
                    <label>Nilai Transaksi (RM) :</label>
                    <c:if test="${actionBean.permohonanUrusan.perjanjianAmaun ne null}"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.permohonanUrusan.perjanjianAmaun}"/></c:if>
                    <c:if test="${actionBean.permohonanUrusan.perjanjianAmaun eq null}"> TIADA DATA </c:if>
                    </p>
                    <p>
                        <label>Tempoh Bayaran (Tahun) :</label>
                    <c:if test="${actionBean.tempohTahun ne null}">${actionBean.tempohTahun}&nbsp;</c:if>
                    <c:if test="${actionBean.tempohTahun eq null}"> TIADA DATA </c:if>
                    </p>
                    <p>
                        <label>Tujuan :</label>
                    <c:if test="${actionBean.permohonanUrusan.sebab ne null}"> ${actionBean.permohonanUrusan.sebab}&nbsp; </c:if>
                    <c:if test="${actionBean.permohonanUrusan.sebab eq null}"> TIADA DATA </c:if>
                    </p>
                    <p>
                        <label>Keterangan :</label>
                    <c:if test="${actionBean.permohonanUrusan.catatan ne null}"> ${actionBean.permohonanUrusan.catatan}&nbsp; </c:if>
                    <c:if test="${actionBean.permohonanUrusan.catatan eq null}"> TIADA DATA </c:if>
                    </p>
                    <br/>
                </fieldset>
            </div>
            <br/>
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>Maklumat Gadaian</legend>
                    <p>
                        <label>Nilai Transaksi (RM) :</label>
                    <c:if test="${actionBean.permohonanUrusan2.perjanjianAmaun ne null}"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.permohonanUrusan2.perjanjianAmaun}"/></c:if>
                    <c:if test="${actionBean.permohonanUrusan2.perjanjianAmaun eq null}"> TIADA DATA </c:if>
                    </p>
                    <p>
                        <label>Tempoh Bayaran (Tahun) :</label>
                    <c:if test="${actionBean.tempohTahunSerentak ne null}">${actionBean.tempohTahunSerentak}&nbsp;</c:if>
                    <c:if test="${actionBean.tempohTahunSerentak eq null}"> TIADA DATA </c:if>
                    </p>
                    <p>
                        <label>Tujuan :</label>
                    <c:if test="${actionBean.permohonanUrusan2.sebab ne null}"> ${actionBean.permohonanUrusan2.sebab}&nbsp; </c:if>
                    <c:if test="${actionBean.permohonanUrusan2.sebab eq null}"> TIADA DATA </c:if>
                    </p>
                    <p>
                        <label>Keterangan :</label>
                    <c:if test="${actionBean.permohonanUrusan2.catatan ne null}"> ${actionBean.permohonanUrusan2.catatan}&nbsp; </c:if>
                    <c:if test="${actionBean.permohonanUrusan2.catatan eq null}"> TIADA DATA </c:if>
                    </p>
                    <br/>
                </fieldset>
            </div>
    </c:if>
</s:form>
