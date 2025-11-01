// Generated from ExprParser.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class ExprParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		NUM=1, PI=2, SQT=3, ID=4, MULT=5, DIV=6, ADD=7, SUB=8, LPAREN=9, RPAREN=10, 
		EQUALS=11, WS=12;
	public static final int
		RULE_start = 0, RULE_expr = 1, RULE_assignment = 2, RULE_square_root = 3;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "expr", "assignment", "square_root"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "'pi'", "'sqrt'", null, "'*'", "'/'", "'+'", "'-'", "'('", 
			"')'", "'='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "NUM", "PI", "SQT", "ID", "MULT", "DIV", "ADD", "SUB", "LPAREN", 
			"RPAREN", "EQUALS", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "ExprParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ExprParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StartContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode EOF() { return getToken(ExprParser.EOF, 0); }
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprParserListener ) ((ExprParserListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprParserListener ) ((ExprParserListener)listener).exitStart(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(8);
			expr(0);
			setState(9);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public Node n;
		public ExprContext left;
		public Token NUM;
		public Token ID;
		public ExprContext expr;
		public Square_rootContext square_root;
		public AssignmentContext assignment;
		public Token op;
		public ExprContext right;
		public TerminalNode NUM() { return getToken(ExprParser.NUM, 0); }
		public TerminalNode ID() { return getToken(ExprParser.ID, 0); }
		public TerminalNode LPAREN() { return getToken(ExprParser.LPAREN, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(ExprParser.RPAREN, 0); }
		public TerminalNode PI() { return getToken(ExprParser.PI, 0); }
		public Square_rootContext square_root() {
			return getRuleContext(Square_rootContext.class,0);
		}
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public TerminalNode MULT() { return getToken(ExprParser.MULT, 0); }
		public TerminalNode DIV() { return getToken(ExprParser.DIV, 0); }
		public TerminalNode ADD() { return getToken(ExprParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(ExprParser.SUB, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprParserListener ) ((ExprParserListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprParserListener ) ((ExprParserListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(29);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(12);
				((ExprContext)_localctx).NUM = match(NUM);

				        ((ExprContext)_localctx).n =  new Literal(Double.parseDouble((((ExprContext)_localctx).NUM!=null?((ExprContext)_localctx).NUM.getText():null)));
				    
				}
				break;
			case 2:
				{
				setState(14);
				((ExprContext)_localctx).ID = match(ID);

				        ((ExprContext)_localctx).n =  new VariableAccess((((ExprContext)_localctx).ID!=null?((ExprContext)_localctx).ID.getText():null));
				    
				}
				break;
			case 3:
				{
				setState(16);
				match(LPAREN);
				setState(17);
				((ExprContext)_localctx).expr = expr(0);
				setState(18);
				match(RPAREN);

				        ((ExprContext)_localctx).n =  ((ExprContext)_localctx).expr.n;
				    
				}
				break;
			case 4:
				{
				setState(21);
				match(PI);

				        ((ExprContext)_localctx).n =  new PI();
				    
				}
				break;
			case 5:
				{
				setState(23);
				((ExprContext)_localctx).square_root = square_root();

				        ((ExprContext)_localctx).n =  ((ExprContext)_localctx).square_root.n;
				    
				}
				break;
			case 6:
				{
				setState(26);
				((ExprContext)_localctx).assignment = assignment();

				        ((ExprContext)_localctx).n =  ((ExprContext)_localctx).assignment.n;
				    
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(43);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(41);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(31);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(32);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==MULT || _la==DIV) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(33);
						((ExprContext)_localctx).right = ((ExprContext)_localctx).expr = expr(9);

						                  if ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals("*"))
						                      ((ExprContext)_localctx).n =  new Multiply(((ExprContext)_localctx).left.n, ((ExprContext)_localctx).right.n);
						                  else
						                      ((ExprContext)_localctx).n =  new Divide(((ExprContext)_localctx).left.n, ((ExprContext)_localctx).right.n);
						              
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(36);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(37);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==SUB) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(38);
						((ExprContext)_localctx).right = ((ExprContext)_localctx).expr = expr(8);

						                  if ((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null).equals("+"))
						                      ((ExprContext)_localctx).n =  new Add(((ExprContext)_localctx).left.n, ((ExprContext)_localctx).right.n);
						                  else
						                      ((ExprContext)_localctx).n =  new Subtract(((ExprContext)_localctx).left.n, ((ExprContext)_localctx).right.n);
						              
						}
						break;
					}
					} 
				}
				setState(45);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignmentContext extends ParserRuleContext {
		public Node n;
		public Token ID;
		public ExprContext expr;
		public TerminalNode ID() { return getToken(ExprParser.ID, 0); }
		public TerminalNode EQUALS() { return getToken(ExprParser.EQUALS, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprParserListener ) ((ExprParserListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprParserListener ) ((ExprParserListener)listener).exitAssignment(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			((AssignmentContext)_localctx).ID = match(ID);
			setState(47);
			match(EQUALS);
			setState(48);
			((AssignmentContext)_localctx).expr = expr(0);

			        ((AssignmentContext)_localctx).n =  new Assignment((((AssignmentContext)_localctx).ID!=null?((AssignmentContext)_localctx).ID.getText():null), ((AssignmentContext)_localctx).expr.n);
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Square_rootContext extends ParserRuleContext {
		public Node n;
		public ExprContext expr;
		public TerminalNode SQT() { return getToken(ExprParser.SQT, 0); }
		public TerminalNode LPAREN() { return getToken(ExprParser.LPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ExprParser.RPAREN, 0); }
		public Square_rootContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_square_root; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprParserListener ) ((ExprParserListener)listener).enterSquare_root(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprParserListener ) ((ExprParserListener)listener).exitSquare_root(this);
		}
	}

	public final Square_rootContext square_root() throws RecognitionException {
		Square_rootContext _localctx = new Square_rootContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_square_root);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			match(SQT);
			setState(52);
			match(LPAREN);
			setState(53);
			((Square_rootContext)_localctx).expr = expr(0);
			setState(54);
			match(RPAREN);

			        ((Square_rootContext)_localctx).n =  new SquareRoot(((Square_rootContext)_localctx).expr.n);
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 8);
		case 1:
			return precpred(_ctx, 7);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\f:\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0003\u0001\u001e\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0005\u0001*\b\u0001\n\u0001\f\u0001-\t\u0001\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0000\u0001\u0002"+
		"\u0004\u0000\u0002\u0004\u0006\u0000\u0002\u0001\u0000\u0005\u0006\u0001"+
		"\u0000\u0007\b<\u0000\b\u0001\u0000\u0000\u0000\u0002\u001d\u0001\u0000"+
		"\u0000\u0000\u0004.\u0001\u0000\u0000\u0000\u00063\u0001\u0000\u0000\u0000"+
		"\b\t\u0003\u0002\u0001\u0000\t\n\u0005\u0000\u0000\u0001\n\u0001\u0001"+
		"\u0000\u0000\u0000\u000b\f\u0006\u0001\uffff\uffff\u0000\f\r\u0005\u0001"+
		"\u0000\u0000\r\u001e\u0006\u0001\uffff\uffff\u0000\u000e\u000f\u0005\u0004"+
		"\u0000\u0000\u000f\u001e\u0006\u0001\uffff\uffff\u0000\u0010\u0011\u0005"+
		"\t\u0000\u0000\u0011\u0012\u0003\u0002\u0001\u0000\u0012\u0013\u0005\n"+
		"\u0000\u0000\u0013\u0014\u0006\u0001\uffff\uffff\u0000\u0014\u001e\u0001"+
		"\u0000\u0000\u0000\u0015\u0016\u0005\u0002\u0000\u0000\u0016\u001e\u0006"+
		"\u0001\uffff\uffff\u0000\u0017\u0018\u0003\u0006\u0003\u0000\u0018\u0019"+
		"\u0006\u0001\uffff\uffff\u0000\u0019\u001e\u0001\u0000\u0000\u0000\u001a"+
		"\u001b\u0003\u0004\u0002\u0000\u001b\u001c\u0006\u0001\uffff\uffff\u0000"+
		"\u001c\u001e\u0001\u0000\u0000\u0000\u001d\u000b\u0001\u0000\u0000\u0000"+
		"\u001d\u000e\u0001\u0000\u0000\u0000\u001d\u0010\u0001\u0000\u0000\u0000"+
		"\u001d\u0015\u0001\u0000\u0000\u0000\u001d\u0017\u0001\u0000\u0000\u0000"+
		"\u001d\u001a\u0001\u0000\u0000\u0000\u001e+\u0001\u0000\u0000\u0000\u001f"+
		" \n\b\u0000\u0000 !\u0007\u0000\u0000\u0000!\"\u0003\u0002\u0001\t\"#"+
		"\u0006\u0001\uffff\uffff\u0000#*\u0001\u0000\u0000\u0000$%\n\u0007\u0000"+
		"\u0000%&\u0007\u0001\u0000\u0000&\'\u0003\u0002\u0001\b\'(\u0006\u0001"+
		"\uffff\uffff\u0000(*\u0001\u0000\u0000\u0000)\u001f\u0001\u0000\u0000"+
		"\u0000)$\u0001\u0000\u0000\u0000*-\u0001\u0000\u0000\u0000+)\u0001\u0000"+
		"\u0000\u0000+,\u0001\u0000\u0000\u0000,\u0003\u0001\u0000\u0000\u0000"+
		"-+\u0001\u0000\u0000\u0000./\u0005\u0004\u0000\u0000/0\u0005\u000b\u0000"+
		"\u000001\u0003\u0002\u0001\u000012\u0006\u0002\uffff\uffff\u00002\u0005"+
		"\u0001\u0000\u0000\u000034\u0005\u0003\u0000\u000045\u0005\t\u0000\u0000"+
		"56\u0003\u0002\u0001\u000067\u0005\n\u0000\u000078\u0006\u0003\uffff\uffff"+
		"\u00008\u0007\u0001\u0000\u0000\u0000\u0003\u001d)+";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}