<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<div id="syorPPT" align="center">
    <table class="tablecloth" align="center">
        <c:choose>
            <c:when test="${actionBean.kodNegeri eq '04'}">
                <tr>
                    <td>
                        Syor : 
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'SL'}">Sokong</c:when>
                            <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'ST'}">Tidak Sokong</c:when>
                            <c:otherwise>
                                ${actionBean.fasaPermohonan.keputusan.nama}&nbsp;
                            </c:otherwise>                                                    
                        </c:choose>                                             
                    </td>
                </tr>
                <c:choose>
                    <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'SL' }">
                        <tr>
                            <td>
                                <font color="red">*</font>Jumlah Isipadu Dipohon :
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
                                Kuantiti :
                            </td>
                            <td>
                                ${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jumlahIsipadu}
                                <c:if test="${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jumlahIsipaduDipohonUom.kod eq 'MP'}">Meterpadu</c:if>
                                <c:if test="${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jumlahIsipaduDipohonUom.kod eq 'KB'}">Ketul Batu</c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Tempoh :
                            </td>
                            <td>
                                ${actionBean.disPermohonanBahanBatu.syaratBahanBatu.tempohDisyor} ${actionBean.disPermohonanBahanBatu.syaratBahanBatu.unitTempohKeluar.nama}
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
                                <c:if test="${actionBean.hakmilikPermohonan.kodMilik.kod eq 'K'}">
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
                                <c:if test="${actionBean.hakmilikPermohonan.kodMilik.kod eq 'K'}">
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
                    </c:when>
                    <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'ST' }">
                        <tr>
                            <td>
                                Sebab :
                            </td>
                            <td>
                                <c:if test="${actionBean.fasaPermohonan.ulasan eq null}">
                        	- &nbsp;
                                </c:if>
                                <c:if test="${actionBean.fasaPermohonan.ulasan ne null}">
                                    ${actionBean.fasaPermohonan.ulasan}&nbsp;
                                </c:if>
                            </td>
                        </tr>    
                    </c:when>
                    <c:otherwise>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:when test="${actionBean.kodNegeri eq '05'}">
                <tr>
                    <td>
                        Syor : 
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'SL'}">Sokong</c:when>
                            <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'ST'}">Tidak Sokong</c:when>
                            <c:otherwise>
                                ${actionBean.fasaPermohonan.keputusan.nama}&nbsp;
                            </c:otherwise>                                                    
                        </c:choose>                                             
                    </td>
                </tr>
                <c:choose>
                    <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'SL' }">
                        <tr>
                            <td>
                                <font color="red">*</font>Jumlah Isipadu Dipohon :
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
                                Jumlah Isipadu Disyor :
                            </td>
                            <td>
                                ${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jumlahIsipadu}
                                <c:if test="${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jumlahIsipaduDipohonUom.kod eq 'MP'}">Meterpadu</c:if>
                                <c:if test="${actionBean.disPermohonanBahanBatu.syaratBahanBatu.jumlahIsipaduDipohonUom.kod eq 'KB'}">Ketul Batu</c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Tempoh :
                            </td>
                            <td>
                                ${actionBean.disPermohonanBahanBatu.syaratBahanBatu.tempohDisyor} ${actionBean.disPermohonanBahanBatu.syaratBahanBatu.unitTempohKeluar.nama}
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
                                <c:if test="${actionBean.hakmilikPermohonan.kodMilik.kod eq 'K'}">
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
                                <c:if test="${actionBean.hakmilikPermohonan.kodMilik.kod eq 'K'}">
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
                    </c:when>
                    <c:when test="${actionBean.fasaPermohonan.keputusan.kod eq 'ST' }">
                        <tr>
                            <td>
                                Sebab :
                            </td>
                            <td>
                                <c:if test="${actionBean.fasaPermohonan.ulasan eq null}">
                        	- &nbsp;
                                </c:if>
                                <c:if test="${actionBean.fasaPermohonan.ulasan ne null}">
                                    ${actionBean.fasaPermohonan.ulasan}&nbsp;
                                </c:if>
                            </td>
                        </tr>    
                    </c:when>
                    <c:otherwise>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
            </c:otherwise>
        </c:choose>
    </table>
</div>