<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }

    function removeNonNumeric( strString )
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for( intIndex = 0; intIndex < strString.length; intIndex++ )
        {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }

    function reload(val) {
        doBlockUI();
        var url = '${pageContext.request.contextPath}/consent/perihal_tanah?selectHakmilik&idHakmilik=' + val;
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

    function reloadEdit(val) {
        doBlockUI();
        var url = '${pageContext.request.contextPath}/consent/perihal_tanah?selectHakmilikEdit&idHakmilik=' + val;
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
    <s:form beanclass="etanah.view.stripes.consent.PerihalTanahActionBean">
        <s:messages/>
        <s:hidden name="permohonan.idPermohonan" />
        <fieldset class="aras1">
            <legend>Senarai Hakmilik Terlibat</legend>

            <p>
                <label>Hakmilik :</label>
                <c:if test="${edit}">
                    <s:select name="idHakmilik" onchange="reloadEdit(this.value);" id="hakmilik">
                        <c:forEach items="${actionBean.senaraiHakmilik}" var="item" varStatus="line">
                            <s:option value="${item.hakmilik.idHakmilik}" style="width:400px">
                                ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                            </s:option>
                        </c:forEach>
                    </s:select>
                </c:if>
                <c:if test="${!edit}">
                    <s:select name="idHakmilik" onchange="reload(this.value);" id="hakmilik">
                        <c:forEach items="${actionBean.senaraiHakmilik}" var="item" varStatus="line">
                            <s:option value="${item.hakmilik.idHakmilik}" style="width:400px">
                                ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                            </s:option>
                        </c:forEach>
                    </s:select>
                </c:if>
            </p>
            <br/>
        </fieldset>
        <br/>

        <div class="subtitle">
            <fieldset class="aras1">
                <s:hidden name="laporanTanah.idLaporan"/>
                <s:hidden name="hakmilikPermohonan.id"/>
                <s:hidden name="laporanTanah.permohonan.idPermohonan"/>
                <legend>Keadaan Tanah</legend>
                <c:if test="${edit}">
                    <p>
                        <label>Jarak Dari Rumah Pemohon (KM) :</label>
                        <s:text name="hakmilikPermohonan.jarakDari" size="40"/>
                    </p>
                    <p>
                        <label>Lokasi :</label>
                        <s:text name="hakmilikPermohonan.lokasi" size="40"/>
                    </p>
                    <p>
                        <label>Keadaan Tanah :</label>
                        <s:textarea name="laporanTanah.keadaanTanah" cols="50" rows="4"/>
                    </p>
                    <p>
                        <label>Maklumat Tambahan :</label>
                        <s:textarea name="hakmilikPermohonan.catatan" cols="50" rows="4"/>
                    </p>
                </c:if>
                <c:if test="${!edit}">
                    <p>
                        <label>Jarak Dari Rumah Pemohon (KM) :</label>
                        <c:if test="${actionBean.hakmilikPermohonan.jarakDari ne null}"> ${actionBean.hakmilikPermohonan.jarakDari}&nbsp; </c:if>
                        <c:if test="${actionBean.hakmilikPermohonan.jarakDari eq null}"> Tiada Data </c:if>

                    </p>
                    <p>
                        <label>Lokasi :</label>
                        <c:if test="${actionBean.hakmilikPermohonan.lokasi ne null}"> ${actionBean.hakmilikPermohonan.lokasi}&nbsp; </c:if>
                        <c:if test="${actionBean.hakmilikPermohonan.lokasi eq null}"> Tiada Data </c:if>
                    </p>
                    <p>
                        <label>Keadaan Tanah :</label>
                        <c:if test="${actionBean.laporanTanah.keadaanTanah ne null}"> ${actionBean.laporanTanah.keadaanTanah}&nbsp; </c:if>
                        <c:if test="${actionBean.laporanTanah.keadaanTanah eq null}"> Tiada Data </c:if>
                    </p>
                    <p>
                        <label>Maklumat Tambahan :</label>
                        <c:if test="${actionBean.hakmilikPermohonan.catatan ne null}"> ${actionBean.hakmilikPermohonan.catatan}&nbsp; </c:if>
                        <c:if test="${actionBean.hakmilikPermohonan.catatan eq null}"> Tiada Data </c:if>
                    </p>
                </c:if>
            </fieldset>
        </div>
        <br/>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Tanah Sekeliling</legend>
                <c:if test="${edit}">
                    <div class="content" align="center">
                        <table class="tablecloth">
                            <tr>
                                <th>&nbsp;</th><th>Jenis Tanah</th><th>Nombor Lot</th><th>Kegunaan</th>
                            </tr>
                            <tr>
                                <th>
                                    Utara
                                </th>
                                <td>
                                    <s:radio name="laporanTanah.sempadanUtaraMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                                    <s:radio name="laporanTanah.sempadanUtaraMilikKerajaan" value="T"/>&nbsp;Milik
                                </td>
                                <td>
                                    <s:text name="laporanTanah.sempadanUtaraNoLot" maxlength="10"/>
                                </td>
                                <td>
                                    <s:text name="laporanTanah.sempadanUtaraKegunaan" maxlength="50" size="50"/>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    Selatan
                                </th>
                                <td>
                                    <s:radio name="laporanTanah.sempadanSelatanMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                                    <s:radio name="laporanTanah.sempadanSelatanMilikKerajaan" value="T"/>&nbsp;Milik
                                </td>
                                <td>
                                    <s:text name="laporanTanah.sempadanSelatanNoLot" maxlength="10"/>
                                </td>
                                <td>
                                    <s:text name="laporanTanah.sempadanSelatanKegunaan" maxlength="50" size="50"/>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    Timur
                                </th>
                                <td>
                                    <s:radio name="laporanTanah.sempadanTimurMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                                    <s:radio name="laporanTanah.sempadanTimurMilikKerajaan" value="T"/>&nbsp;Milik
                                </td>
                                <td>
                                    <s:text name="laporanTanah.sempadanTimurNoLot" maxlength="10"/>
                                </td>
                                <td>
                                    <s:text name="laporanTanah.sempadanTimurKegunaan" maxlength="50" size="50"/>
                                </td>
                            </tr><tr>
                                <th>
                                    Barat
                                </th>
                                <td>
                                    <s:radio name="laporanTanah.sempadanBaratMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                                    <s:radio name="laporanTanah.sempadanBaratMilikKerajaan" value="T"/>&nbsp;Milik
                                </td>
                                <td>
                                    <s:text name="laporanTanah.sempadanBaratNoLot" maxlength="10"/>
                                </td>
                                <td>
                                    <s:text name="laporanTanah.sempadanBaratKegunaan" maxlength="50" size="50"/>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <p>
                        <label>&nbsp;</label>
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
                </c:if>
                <c:if test="${!edit}">
                    <div class="content" align="center">
                        <table class="tablecloth">
                            <tr>
                                <th>&nbsp;</th><th>Jenis Tanah</th><th>Nombor Lot</th><th>Kegunaan</th>
                            </tr>
                            <tr>
                                <th>
                                    Utara
                                </th>
                                <td>
                                    <c:if test="${actionBean.laporanTanah.sempadanUtaraMilikKerajaan ne null}">
                                        <c:if test="${actionBean.laporanTanah.sempadanUtaraMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                        <c:if test="${actionBean.laporanTanah.sempadanUtaraMilikKerajaan eq 'T'}">Milik</c:if>
                                    </c:if>
                                    <c:if test="${actionBean.laporanTanah.sempadanUtaraMilikKerajaan eq null}">Tiada Data</c:if>
                                </td>
                                <td>
                                    <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot ne null}"> ${actionBean.laporanTanah.sempadanUtaraNoLot}&nbsp; </c:if>
                                    <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot eq null}"> Tiada Data </c:if>
                                </td>
                                <td>
                                    <c:if test="${actionBean.laporanTanah.sempadanUtaraKegunaan ne null}"> ${actionBean.laporanTanah.sempadanUtaraKegunaan}&nbsp; </c:if>
                                    <c:if test="${actionBean.laporanTanah.sempadanUtaraKegunaan eq null}"> Tiada Data </c:if>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    Selatan
                                </th>
                                <td>
                                    <c:if test="${actionBean.laporanTanah.sempadanSelatanMilikKerajaan ne null}">
                                        <c:if test="${actionBean.laporanTanah.sempadanSelatanMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                        <c:if test="${actionBean.laporanTanah.sempadanSelatanMilikKerajaan eq 'T'}">Milik</c:if>
                                    </c:if>
                                    <c:if test="${actionBean.laporanTanah.sempadanSelatanMilikKerajaan eq null}">Tiada Data</c:if>
                                </td>
                                <td>
                                    <c:if test="${actionBean.laporanTanah.sempadanSelatanNoLot ne null}"> ${actionBean.laporanTanah.sempadanSelatanNoLot}&nbsp; </c:if>
                                    <c:if test="${actionBean.laporanTanah.sempadanSelatanNoLot eq null}"> Tiada Data </c:if>
                                </td>
                                <td>
                                    <c:if test="${actionBean.laporanTanah.sempadanSelatanKegunaan ne null}"> ${actionBean.laporanTanah.sempadanSelatanKegunaan}&nbsp; </c:if>
                                    <c:if test="${actionBean.laporanTanah.sempadanSelatanKegunaan eq null}"> Tiada Data </c:if>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    Timur
                                </th>
                                <td>
                                    <c:if test="${actionBean.laporanTanah.sempadanTimurMilikKerajaan ne null}">
                                        <c:if test="${actionBean.laporanTanah.sempadanTimurMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                        <c:if test="${actionBean.laporanTanah.sempadanTimurMilikKerajaan eq 'T'}">Milik</c:if>
                                    </c:if>
                                    <c:if test="${actionBean.laporanTanah.sempadanTimurMilikKerajaan eq null}">Tiada Data</c:if>
                                </td>
                                <td>
                                    <c:if test="${actionBean.laporanTanah.sempadanTimurNoLot ne null}"> ${actionBean.laporanTanah.sempadanTimurNoLot}&nbsp; </c:if>
                                    <c:if test="${actionBean.laporanTanah.sempadanTimurNoLot eq null}"> Tiada Data </c:if>
                                </td>
                                <td>
                                    <c:if test="${actionBean.laporanTanah.sempadanTimurKegunaan ne null}"> ${actionBean.laporanTanah.sempadanTimurKegunaan}&nbsp; </c:if>
                                    <c:if test="${actionBean.laporanTanah.sempadanTimurKegunaan eq null}"> Tiada Data </c:if>
                                </td>
                            </tr><tr>
                                <th>
                                    Barat
                                </th>
                                <td>
                                    <c:if test="${actionBean.laporanTanah.sempadanBaratMilikKerajaan ne null}">
                                        <c:if test="${actionBean.laporanTanah.sempadanBaratMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                        <c:if test="${actionBean.laporanTanah.sempadanBaratMilikKerajaan eq 'T'}">Milik</c:if>
                                    </c:if>
                                    <c:if test="${actionBean.laporanTanah.sempadanBaratMilikKerajaan eq null}">Tiada Data</c:if>
                                </td>
                                <td>
                                    <c:if test="${actionBean.laporanTanah.sempadanBaratNoLot ne null}"> ${actionBean.laporanTanah.sempadanBaratNoLot}&nbsp; </c:if>
                                    <c:if test="${actionBean.laporanTanah.sempadanBaratNoLot eq null}"> Tiada Data </c:if>
                                </td>
                                <td>
                                    <c:if test="${actionBean.laporanTanah.sempadanBaratKegunaan ne null}"> ${actionBean.laporanTanah.sempadanBaratKegunaan}&nbsp; </c:if>
                                    <c:if test="${actionBean.laporanTanah.sempadanBaratKegunaan eq null}"> Tiada Data </c:if>
                                </td>
                            </tr>
                        </table>
                    </div>
                </c:if>

            </fieldset>
        </div>

    </s:form>
</div>



