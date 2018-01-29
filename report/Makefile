all:
	latex report.tex
	latex report.tex
	bibtex report
	latex report.tex
	latex report.tex
	dvips -o report.ps report.dvi
	ps2pdf -sPAPERSIZE=a4 -DMaxSubsetPct=100 -dCompatibilityLevel=1.2 -dSubsetFonts=true -dEmbedAllFonts=true report.ps report.pdf

clean:
	rm -f *.toc *.ps *.log *.lof *.lot *.dvi *.aux *.blg *.bbl
