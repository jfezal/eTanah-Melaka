<%-- 
    Document   : borang_lokasi_aduan
    Created on : Jan 18, 2010, 4:09:02 PM
    Author     : nurshahida.radzi
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">

    function refreshPagesenarai(){

        var url = '${pageContext.request.contextPath}/penguatkuasaan/senarai_aduan?refreshpage';
        window.location = url;

    }

    function textCounter(field, countfield, maxlimit) {
        if (field.value.length > maxlimit) // if too long...trim it!
            field.value = field.value.substring(0, maxlimit);
        // otherwise, update 'characters left' counter
        else
            countfield.value = maxlimit - field.value.length;
    }
    function test(f) {
        $(f).clearForm();
    }

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

    function validate(){
        if($('#bpm').val() == "")
        {
            alert("Sila pilih Bandar/Pekan/Mukim");
            $('#bpm').focus();
            return false;
        }

        return true;
    }
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<s:form beanclass="etanah.view.penguatkuasaan.MaklumatLokasiAduanActionBean">
    <s:messages/>
    <div class="subtitle">

        <fieldset class="aras1">
            <c:choose>
                <c:when test="${actionBean.kodNegeri eq '05'}">
                    <legend>
                        Lokasi Kejadian
                    </legend>
                    <div class="instr-fieldset">
                        <font color="red">PERINGATAN:</font>(Dimana tempat yang hendak diadukan).
                    </div>
                </c:when>
                <c:otherwise>
                    <legend>
                        Lokasi Kejadian
                    </legend>
                </c:otherwise>
            </c:choose>

            <c:if test="${form}">
                <div class="content">
                    <p>
                        <label>Lokasi Aduan :</label>
                        ${actionBean.permohonan.cawangan.name}&nbsp;
                    </p>
                    <%--       <p>
                               <label>Bandar/Pekan/Mukim :</label>
                               <s:select name="bandarPekanMukim.kod" value="${actionBean.aduanLokasi.bandarPekanMukim.kod}">
                                   <s:option value="">Sila Pilih</s:option>
                                   <s:options-collection collection="${listUtil.senaraiKodBandarMukim}" label="nama" value="kod" sort="nama" />
                               </s:select>
                               &nbsp;
                           </p>--%>
                    <%-- <p>
                         <label>Bandar/Pekan/Mukim :</label>

                         ${actionBean.aduanLokasi.bandarPekanMukim.nama}&nbsp;--%>
                    <%--  <s:hidden name="bandarPekanMukim.kod " value="${actionBean.aduanLokasi.bandarPekanMukim.kod}"/> &nbsp;
                  </p>--%>
                    <p>
                        <label><em>*</em>Bandar/Pekan/Mukim :</label>
                        <s:select name="kod" id="bpm" value="${actionBean.aduanLokasi.bandarPekanMukim.kod}">
                            <s:option value=""> Sila Pilih </s:option>
                            <c:forEach items="${actionBean.listBandarPekanMukim}" var="line">
                                <s:option value="${line.kod}">${line.bandarPekanMukim} - ${line.nama}</s:option>
                            </c:forEach>
                        </s:select>
                        &nbsp;
                    </p>
                    <p>
                        <label>Jenis Tanah :</label>
                        <s:select name="pemilikan.kod" value="${actionBean.aduanLokasi.pemilikan.kod}">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.kodPemilikanList}" label="nama" value="kod" sort="nama" />
                            <%--<s:options-collection collection="${listUtil.senaraiKodPemilikan}" label="nama" value="kod" sort="nama" />--%>
                        </s:select>
                        &nbsp;
                    </p>
                    <p>
                        <label>Nombor:</label>
                        <s:select name="kodLot.kod" value="${actionBean.aduanLokasi.kodLot.kod}">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodLot}" label="nama" value="kod" sort="nama" />
                        </s:select>
                            <c:choose>
                                <c:when test="${actionBean.kodNegeri eq '05'}">
                                    <s:text name="aduanLokasi.noLot"/> &nbsp;
                                </c:when>
                                <c:otherwise>
                                    <s:text name="aduanLokasi.noLot" /> &nbsp;
                                </c:otherwise>
                            </c:choose>                        
                    </p>
                    <%--    <p>
                            <label>Lokasi :</label>
                            <s:textarea name="aduanLokasi.lokasi" id="message" rows="5" cols="50" onkeydown="textCounter(this.form.message,this.form.remLen,100);" onkeyup="textCounter(this.form.message,this.form.remLen,100);"/>&nbsp;
                        </p>--%>
                    <table>
                        <tr>
                            <td>
                                <p><label>Lokasi :</label></p>
                            </td> 
                            <td>
                                <s:textarea name="aduanLokasi.lokasi" id="message" rows="5" cols="50"/>
                            </td>
                        </tr>
                    </table>

                    <p align="right">
                        <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
                        <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
                        <%--<s:button class="btn" onclick="doSubmit(this.form, this.name, 'page_div');" name="simpan1" value="Simpan"/>--%>
                        <s:button class="btn" onclick="if(validate())doSubmit(this.form, this.name, 'page_div');" name="simpan1" value="Simpan"/>

                    </p>

                </div></c:if>
            <c:if test="${view}">
                <div class="content">

                    <p>
                        <label>Lokasi Aduan :</label>
                        <font style="text-transform: uppercase">${actionBean.permohonan.cawangan.name}&nbsp;</font>
                    </p>

                    <p>
                        <label>Bandar/Pekan/Mukim :</label>
                        <c:choose>
                            <c:when test="${actionBean.aduanLokasi.bandarPekanMukim.bandarPekanMukim eq '00'}">
                                -
                            </c:when>
                            <c:otherwise>
                                <font style="text-transform: uppercase">${actionBean.aduanLokasi.bandarPekanMukim.bandarPekanMukim} - ${actionBean.aduanLokasi.bandarPekanMukim.nama}&nbsp;</font>
                            </c:otherwise>
                        </c:choose>

                    </p>

                    <p>
                        <label>Jenis Tanah :</label>
                        <c:choose>
                            <c:when test="${actionBean.aduanLokasi.pemilikan.nama eq null}">
                                -
                            </c:when>
                            <c:otherwise>
                                <font style="text-transform: uppercase">${actionBean.aduanLokasi.pemilikan.nama}&nbsp;</font>
                            </c:otherwise>
                        </c:choose>
                    </p>

                    <p>
                        <label>Nombor :</label>
                        <c:choose>
                            <c:when test="${actionBean.aduanLokasi.noLot eq null}">
                                -
                            </c:when>
                            <c:otherwise>
                                <font style="text-transform: uppercase">${actionBean.aduanLokasi.kodLot.nama}&nbsp;</font><c:if test="${actionBean.aduanLokasi.noLot ne null}">: &nbsp;</c:if>
                                <font style="text-transform: uppercase">${actionBean.aduanLokasi.noLot}&nbsp;</font>
                            </c:otherwise>
                        </c:choose>
                    </p>
                    <table>
                        <tr>
                            <td>

                                <p><label>Lokasi :</label></p>
                            </td> <td>
                                <font size="2px;" style="text-transform: uppercase">${actionBean.aduanLokasi.lokasi}&nbsp;</font></td>
                        </tr>
                    </table>

                </div>
            </c:if>
        </fieldset>
    </div>
</s:form>