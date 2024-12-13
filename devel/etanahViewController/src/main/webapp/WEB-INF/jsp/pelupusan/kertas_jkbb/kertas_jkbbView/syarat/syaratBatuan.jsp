<%-- 
    Document   : syaratBatuan
    Created on : Oct 22, 2013, 3:33:55 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pelupusan.KertasJKBBV2ActionBean" name="form">
    <c:choose>
        <c:when test="${actionBean.kodNegeri eq '04'}">
            <table class="tablecloth" border="0">
                <tr>
                    <td>
                        Jumlah Isipadu Dipohon :
                    </td>
                    <td>
                        ${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jumlahIsipaduDipohon}
                        <c:if test="${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jumlahIsipaduDipohonUom.kod eq 'MP'}">Meterpadu</c:if>                                      
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Tempoh Dipohon :
                        </td>
                        <td>
                        ${actionBean.disPermohonanBahanBatu.syaratBahanBatu.tempohKeluar} ${actionBean.disPermohonanBahanBatu.syaratBahanBatu.unitTempohKeluar.nama}                            
                    </td>
                </tr>
                <tr>
                    <td>
                        Kuantiti yang disyorkan :
                    </td>
                    <td>
                        ${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jumlahIsipadu}
                        <c:if test="${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jumlahIsipaduDipohonUom.kod eq 'MP'}">Meterpadu</c:if>
                        <c:if test="${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jumlahIsipaduDipohonUom.kod eq 'KB'}">Ketul Batu</c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Tempoh yang disyorkan :
                        </td>
                        <td>
                        ${actionBean.disPermohonanBahanBatu.syaratBahanBatu.tempohDisyor} ${actionBean.disPermohonanBahanBatu.syaratBahanBatu.tempohSyorUom.nama}
                    </td>
                </tr>
                <tr>
                    <td>
                        Kadar Bayaran (RM) : 
                    </td>
                    <td>
                        <c:if test="${actionBean.hakmilikPermohonan.kodMilik.kod eq 'H'}">
                            <c:if test="${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'TM' or actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'BG'
                                          or actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'PS' or actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'TL' or actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'RP'
                                          or actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'BT'}">
                                <s:format value="${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.royaltiTanahMilik}" formatPattern="##0.00"/> <span style="text-transform:lowercase">se${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.royaltiTanahMilikUom.nama}</span>
                            </c:if>
                        </c:if>
                        <c:if test="${actionBean.hakmilikPermohonan.kodMilik.kod eq 'K' or actionBean.hakmilikPermohonan.kodMilik.kod eq 'R' or actionBean.hakmilikPermohonan.kodMilik.kod eq 'L'}">
                            <c:if test="${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'TM' or actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'BG'
                                          or actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'PS' or actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'TL' or actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'RP'
                                          or actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'BT'}">
                                <s:format value="${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.royaltiTanahKerajaan}" formatPattern="##0.00"/> <span style="text-transform:lowercase">se${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.royaltiTanahKerajaanUom.nama}</span>
                            </c:if>
                        </c:if>
                    </td>                            
                </tr>
                <tr>
                    <td>
                        Jumlah bayaran yang dikenakan (RM) :
                    </td>
                    <td>
                        <c:if test="${actionBean.hakmilikPermohonan.kodMilik.kod eq 'H'}">
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
                        x ${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jumlahIsipadu} = RM ${actionBean.disPermohonanBahanBatu.jumlahKeneBayar}
                    </td>

                </tr>
                <tr>
                    <td>
                        Cagaran Permit (RM) : 
                    </td>
                    <td>
                        <s:format formatPattern="###,###,##0.00" value="${actionBean.disPermohonanBahanBatu.cagarKeneBayar}"/> 
                    </td>
                </tr>
                <tr>
                    <td>
                        Cagaran Jalan (RM) :
                    </td>
                    <td>
                        ${actionBean.disPermohonanBahanBatu.cagarJalan}
                    </td>
                </tr>
                <tr>
                    <td>
                        Bayaran Kupon (RM) : 
                    </td>
                    <td>
                        <s:format formatPattern="#,###.00" value="${actionBean.disPermohonanBahanBatu.kuponAmaun}"/> * ${actionBean.disPermohonanBahanBatu.kuponQty} = RM ${actionBean.disPermohonanBahanBatu.kupon}
                    </td>
                </tr>
                <tr>
                    <td>
                        Jumlah Keseluruhan Bayaran (RM) :
                    </td>
                    <td>
                        <s:format formatPattern="#,###.00" value="${actionBean.disPermohonanBahanBatu.totalAll}"/>
                    </td>
                </tr>
            </table>            
        </c:when>
        <c:when test="${actionBean.kodNegeri eq '05'}">
            <table class="tablecloth" border="0">
                <tr>
                    <td>
                        Jumlah Isipadu Dipohon :
                    </td>
                    <td>
                        ${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jumlahIsipaduDipohon}
                        <c:if test="${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jumlahIsipaduDipohonUom.kod eq 'MP'}">Meterpadu</c:if>                                      
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Tempoh Dipohon :
                        </td>
                        <td>
                        ${actionBean.disPermohonanBahanBatu.syaratBahanBatu.tempohKeluar} ${actionBean.disPermohonanBahanBatu.syaratBahanBatu.unitTempohKeluar.nama}                            
                    </td>
                </tr>
                <tr>
                    <td>
                        Kuantiti yang disyorkan :
                    </td>
                    <td>
                        ${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jumlahIsipadu}
                        <c:if test="${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jumlahIsipaduDipohonUom.kod eq 'MP'}">Meterpadu</c:if>
                        <c:if test="${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jumlahIsipaduDipohonUom.kod eq 'KB'}">Ketul Batu</c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Tempoh yang disyorkan :
                        </td>
                        <td>
                        ${actionBean.disPermohonanBahanBatu.syaratBahanBatu.tempohDisyor} ${actionBean.disPermohonanBahanBatu.syaratBahanBatu.tempohSyorUom.nama}
                    </td>
                </tr>
                <tr>
                    <td>
                        Kadar Bayaran (RM) : 
                    </td>
                    <td>
                        <c:if test="${actionBean.hakmilikPermohonan.kodMilik.kod eq 'H'}">
                            <c:if test="${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'TM' or actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'BG'
                                          or actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'PS' or actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'TL' or actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'RP'
                                          or actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'BT'}">
                                <s:format value="${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.royaltiTanahMilik}" formatPattern="##0.00"/> <span style="text-transform:lowercase">se${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.royaltiTanahMilikUom.nama}</span>
                            </c:if>
                        </c:if>
                        <c:if test="${actionBean.hakmilikPermohonan.kodMilik.kod eq 'K' or actionBean.hakmilikPermohonan.kodMilik.kod eq 'R' or actionBean.hakmilikPermohonan.kodMilik.kod eq 'L'}">
                            <c:if test="${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'TM' or actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'BG'
                                          or actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'PS' or actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'TL' or actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'RP'
                                          or actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.kod eq 'BT'}">
                                <s:format value="${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.royaltiTanahKerajaan}" formatPattern="##0.00"/> <span style="text-transform:lowercase">se${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jenisBahanBatu.royaltiTanahKerajaanUom.nama}</span>
                            </c:if>
                        </c:if>
                    </td>                            
                </tr>
                <tr>
                    <td>
                        Jumlah bayaran yang dikenakan (RM) :
                    </td>
                    <td>
                        <c:if test="${actionBean.hakmilikPermohonan.kodMilik.kod eq 'H'}">
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
                        x ${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jumlahIsipadu} = RM ${actionBean.disPermohonanBahanBatu.jumlahKeneBayar}
                    </td>

                </tr>
                <tr>
                    <td>
                        Wang Cagaran yang dikenakan (RM) : 
                    </td>
                    <td>
                        <s:format formatPattern="###,###,##0.00" value="${actionBean.disPermohonanBahanBatu.cagarKeneBayar}"/> 
                    </td>
                </tr>
                <tr>
                    <td>
                        Cagaran Jalan (RM) :
                    </td>
                    <td>
                        ${actionBean.disPermohonanBahanBatu.cagarJalan}
                    </td>
                </tr>
                <tr>
                    <td>
                        Bayaran Kupon (RM) : 
                    </td>
                    <td>
                        <s:format formatPattern="#,###.00" value="${actionBean.disPermohonanBahanBatu.kuponAmaun}"/> * ${actionBean.disPermohonanBahanBatu.kuponQty} = RM ${actionBean.disPermohonanBahanBatu.kupon}
                    </td>
                </tr>
                <tr>
                    <td>
                        Jumlah Keseluruhan Bayaran (RM) :
                    </td>
                    <td>
                        <s:format formatPattern="#,###.00" value="${actionBean.disPermohonanBahanBatu.totalAll}"/>
                    </td>
                </tr>
            </table>
        </c:when>
        <c:otherwise></c:otherwise>
    </c:choose>


</s:form>

