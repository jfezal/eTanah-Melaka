<%-- 
    Document   : syaratBatuan
    Created on : Oct 22, 2013, 2:46:40 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:hidden name="idMh" id="idMh" value="${actionBean.hakmilikPermohonan.id}"/>
<c:choose>
    <c:when test="${actionBean.kodNegeri eq '04'}">
        <table class="tablecloth" border="0">
            <tr>
                <td>Jumlah Isipadu Dipohon :</td>
                <td>
                    ${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jumlahIsipaduDipohon}
                    <c:if test="${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jumlahIsipaduDipohonUom.kod eq 'MP'}">Meterpadu</c:if>
                    </td>
                </tr>
                <tr>
                    <td>Tempoh Dipohon :</td>
                    <td>
                    ${actionBean.disPermohonanBahanBatu.syaratBahanBatu.tempohKeluar} ${actionBean.disPermohonanBahanBatu.syaratBahanBatu.unitTempohKeluar.nama}
                </td>
            </tr>
            <tr>
                <td><font color="red" size="4">*</font>Kuantiti yang disyorkan :</td>
                <td>
                    <s:text name="disPermohonanBahanBatu.syaratBahanBatu.jumlahIsipadu" id="kuantitiSyarat" onkeyup="calculateSyarat()"/>  
                    <c:if test="${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jumlahIsipaduDipohonUom.kod eq 'MP'}">Meterpadu</c:if>
                    <c:if test="${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jumlahIsipaduDipohonUom.kod eq 'KB'}">Ketul Batu</c:if>
                    <s:hidden name="disPermohonanBahanBatu.syaratBahanBatu.jumlahIsipaduDipohonUom.kod"/>
                </td>
            </tr>
            <tr>
                <td><font color="red" size="4">*</font>Tempoh yang disyorkan :</td>
                <td>
                    <s:text name="disPermohonanBahanBatu.syaratBahanBatu.tempohDisyor" id="disPermohonanBahanBatu.syaratBahanBatu.tempohDisyor"/> 
                    <s:select name="unitTempohUOM" id="unitTempohUOM" value="${actionBean.disPermohonanBahanBatu.syaratBahanBatu.tempohSyorUom.kod}">
                        <s:option value="">Sila Pilih</s:option>
                        <s:option value="HR">Hari</s:option>
                        <s:option value="B">Bulan</s:option>
                        <s:option value="T">Tahun</s:option>
                    </s:select>
                </td>
            </tr>
            <tr>
                <td>Kadar Bayaran (RM) : </td>
                <td><c:if test="${actionBean.hakmilikPermohonan.kodMilik.kod eq 'H'}">
                        <c:if test="${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'TM' or actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'BG'
                                      or actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'PS' or actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'TL' or actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'RP'
                                      or actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'BT'}">
                            <s:format value="${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.royaltiTanahMilik}" formatPattern="##0.00"/> <span style="text-transform:lowercase">se${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.royaltiTanahMilikUom.nama}</span>
                            <s:hidden name="bayaranSyarat" id="bayaranSyarat" value="${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.royaltiTanahMilik}"/>
                        </c:if>
                    </c:if>
                    <c:if test="${actionBean.hakmilikPermohonan.kodMilik.kod eq 'K' or actionBean.hakmilikPermohonan.kodMilik.kod eq 'R' or actionBean.hakmilikPermohonan.kodMilik.kod eq 'L'}">
                        <c:if test="${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'TM' or actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'BG'
                                      or actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'PS' or actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'TL' or actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'RP'
                                      or actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'BT'}">
                            <s:format value="${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.royaltiTanahKerajaan}" formatPattern="##0.00"/> <span style="text-transform:lowercase">se${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.royaltiTanahKerajaanUom.nama}</span>
                            <s:hidden name="bayaranSyarat" id="bayaranSyarat" value="${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.royaltiTanahKerajaan}"/>
                        </c:if>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td>Jumlah bayaran yang dikenakan (RM) :</td>
                <td><c:if test="${actionBean.hakmilikPermohonan.kodMilik.kod eq 'H'}">
                        <c:if test="${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'TM' or actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'BG'
                                      or actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'PS' or actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'TL' or actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'RP'
                                      or actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'BT'}">
                            <s:format value="${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.royaltiTanahMilik}" formatPattern="##0.00"/>
                        </c:if>
                    </c:if>
                    <c:if test="${actionBean.hakmilikPermohonan.kodMilik.kod eq 'K' or actionBean.hakmilikPermohonan.kodMilik.kod eq 'R' or actionBean.hakmilikPermohonan.kodMilik.kod eq 'L'}">
                        <c:if test="${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'TM' or actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'BG'
                                      or actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'PS' or actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'TL' or actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'RP'
                                      or actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'BT'}">
                            <s:format value="${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.royaltiTanahKerajaan}" formatPattern="##0.00"/>
                        </c:if>
                    </c:if>
                    x <s:text name="disPermohonanBahanBatu.syaratBahanBatu.jumlahIsipadu" id="kuantitiJumlahSyarat" readonly="true"/> = RM <s:text name="disPermohonanBahanBatu.jumlahKeneBayar" id="jumlahSyarat" formatPattern="###,###,##0.00" readonly="true"/>

                </td>
            </tr>
            <tr>
                <td>
                    <font color="red" size="4">*</font>Cagaran Permit (RM) : 
                </td>
                <td><s:text name="disPermohonanBahanBatu.cagarKeneBayar" id="cagaranSyarat" formatPattern="###,###,##0.00" readonly="true"/>
                </td>
            </tr>
            <tr>
                <td>Cagaran Jalan (RM) :</td>
                <td><s:text name="disPermohonanBahanBatu.cagarJalan" id="cagarJalan" formatPattern="###,###,##0.00" onblur="calculateBayarKupon()"/>
                </td>
            </tr>
            <tr>
                <td><font color="red" size="4">*</font>Bayaran Kupon (RM) : </td>
                <td>
                    <s:format formatPattern="#,##.00" value="${actionBean.disPermohonanBahanBatu.kuponAmaun}"/>  * <s:text name="disPermohonanBahanBatu.kuponQty" id="kuponQty" onkeyup="calculateBayarKupon()"/> = RM <s:text name="disPermohonanBahanBatu.kupon" id="kupon" readonly="true"/>
                    <s:hidden name="disPermohonanBahanBatu.kuponAmaun" id="kuponAmaun" value="${actionBean.disPermohonanBahanBatu.kuponAmaun}"/>
                </td> 
            </tr>
            <tr>
                <td>Jumlah Keseluruhan Bayaran (RM) :</td>
                <td><s:text name="disPermohonanBahanBatu.totalAll" id="totalAll" readonly="true"/>
                </td>    
            </tr>
        </table>           
    </c:when>
    <c:when test="${actionBean.kodNegeri eq '05'}">

    </c:when>
    <c:otherwise></c:otherwise>
</c:choose>

